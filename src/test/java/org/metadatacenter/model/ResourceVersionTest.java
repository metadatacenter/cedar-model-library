package org.metadatacenter.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceVersionTest {

  @Test
  void rejectsMalformedNumericVersions() {
    for (String value : java.util.List.of("1.-1.0", "-1.2.3", "+1.0.0", "1.0.0.", "01.0.0", "2147483648.0.1", "0.0.0")) {
      org.junit.jupiter.api.Assertions.assertFalse(ResourceVersion.forValueWithValidation(value).isValid(), value);
    }
  }

  @Test
  void acceptsThreeNonNegativeIntegersThatAreNotAllZero() {
    for (String value : List.of("0.0.1", "0.1.0", "1.0.0", "10.20.30", "2147483647.0.0")) {
      assertTrue(ResourceVersion.forValueWithValidation(value).isValid(), value);
    }
  }

  @Test
  void rejectsVersionsThatAreNotThreeIntegers() {
    for (String value : Arrays.asList(null, "", "0.0.0", "01.0.0", "2147483648.0.0", "1", "1.0", "1.0.0.0",
        "1.0.0-SNAPSHOT", "v1.0.0", " 1.0.0", "a.b.c")) {
      assertFalse(ResourceVersion.forValueWithValidation(value).isValid(), String.valueOf(value));
    }
  }

  // The version chain refuses a draft that does not exceed its published source, and picks the
  // latest published version with this ordering.
  @Test
  void ordersByMajorThenMinorThenPatchAsNumbers() {
    List<String> ascending = List.of(
        "0.0.1", "0.0.2", "0.0.10", "0.1.0", "0.2.0", "0.10.0", "1.0.0", "1.9.9", "1.10.0", "2.0.0", "10.0.0");
    for (int i = 0; i < ascending.size(); i++) {
      for (int j = 0; j < ascending.size(); j++) {
        ResourceVersion first = ResourceVersion.forValueWithValidation(ascending.get(i));
        ResourceVersion second = ResourceVersion.forValueWithValidation(ascending.get(j));
        assertEquals(Integer.signum(Integer.compare(i, j)), Integer.signum(first.compareTo(second)),
            ascending.get(i) + " against " + ascending.get(j));
      }
    }
  }

  // A stored version that does not parse never outranks one that does: the resource server accepts a
  // well-formed new version over a malformed old one, and the version chain never picks a malformed
  // published version as the latest.
  @Test
  void aWellFormedVersionOrdersAfterAMalformedOne() {
    ResourceVersion wellFormed = ResourceVersion.forValueWithValidation("0.0.1");
    for (String value : Arrays.asList(null, "invalid", "99.99.99.99")) {
      ResourceVersion malformed = ResourceVersion.forValueWithValidation(value);
      assertTrue(malformed.isBefore(wellFormed), String.valueOf(value));
      assertFalse(wellFormed.isBefore(malformed), String.valueOf(value));
    }
    assertFalse(wellFormed.isBefore(null));
  }

  // The resource server predicts a new draft's version with this, and assigns it when the draft is made.
  @Test
  void nextPatchVersionIncrementsOnlyThePatch() {
    ResourceVersion current = ResourceVersion.forValueWithValidation("1.2.9");

    ResourceVersion next = current.nextPatchVersion();

    assertEquals("1.2.10", next.getValue());
    assertTrue(next.isValid());
    assertTrue(current.isBefore(next));
  }


  @Test
  void comparesNumericComponentsByValueBeyondTheIntegerCache() {
    ResourceVersion earlier = ResourceVersion.forValueWithValidation("200.0.1");
    ResourceVersion later = ResourceVersion.forValueWithValidation("200.0.2");

    assertTrue(earlier.isBefore(later));
    assertTrue(earlier.compareTo(later) < 0);
    assertTrue(later.compareTo(earlier) > 0);
  }

  @Test
  void equalVersionsHaveEqualHashCodes() {
    ResourceVersion first = ResourceVersion.forValueWithValidation("200.130.140");
    ResourceVersion second = ResourceVersion.forValueWithValidation("200.130.140");

    assertEquals(first, second);
    assertEquals(first.hashCode(), second.hashCode());
    assertEquals(1, new HashSet<>(java.util.List.of(first, second)).size());
  }

  @Test
  void distinctPatchVersionsAreNotEqualBeyondTheIntegerCache() {
    ResourceVersion first = ResourceVersion.forValueWithValidation("200.130.140");
    ResourceVersion second = ResourceVersion.forValueWithValidation("200.130.141");

    assertNotEquals(first, second);
  }

  // A new copy is stamped with this version, and it must be the same version a client's "0.0.1" is.
  @Test
  void theFirstVersionIsAValidVersion() {
    assertTrue(ResourceVersion.ZERO_ZERO_ONE.isValid());
    assertEquals(ResourceVersion.forValueWithValidation("0.0.1"), ResourceVersion.ZERO_ZERO_ONE);
    assertEquals("0.0.2", ResourceVersion.ZERO_ZERO_ONE.nextPatchVersion().getValue());
  }

  // Every schema artifact read from the graph gets its version from forValue, the JSON creator.
  @Test
  void aVersionReadFromJsonOrdersByItsNumbers() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ResourceVersion ten = mapper.readValue("\"10.0.0\"", ResourceVersion.class);
    ResourceVersion nine = mapper.readValue("\"9.0.0\"", ResourceVersion.class);

    assertTrue(ten.isValid());
    assertTrue(nine.isBefore(ten));
    assertFalse(ten.isBefore(nine));
    assertEquals(ResourceVersion.forValueWithValidation("10.0.0"), ten);
    assertEquals("10.0.0", mapper.writeValueAsString(ten).replace("\"", ""));
    assertTrue(ResourceVersion.forValue("9.0.0").isBefore(ResourceVersion.forValue("10.0.0")));
  }

  @Test
  void aMalformedVersionReadFromJsonKeepsItsText() throws Exception {
    ResourceVersion malformed = new ObjectMapper().readValue("\"v1\"", ResourceVersion.class);

    assertFalse(malformed.isValid());
    assertEquals("v1", malformed.getValue());
  }

  @Test
  void invalidVersionsCompareConsistently() {
    ResourceVersion first = ResourceVersion.forValueWithValidation("invalid-a");
    ResourceVersion second = ResourceVersion.forValueWithValidation("invalid-b");

    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
    assertEquals(0, first.compareTo(ResourceVersion.forValueWithValidation("invalid-a")));
  }
}
