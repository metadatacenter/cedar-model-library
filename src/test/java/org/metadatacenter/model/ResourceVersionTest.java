package org.metadatacenter.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResourceVersionTest {

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

  @Test
  void invalidVersionsCompareConsistently() {
    ResourceVersion first = ResourceVersion.forValueWithValidation("invalid-a");
    ResourceVersion second = ResourceVersion.forValueWithValidation("invalid-b");

    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
    assertEquals(0, first.compareTo(ResourceVersion.forValueWithValidation("invalid-a")));
  }
}
