package org.metadatacenter.model.policy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.*;

class SourcePolicyTest {
  @Test void preservesNumbersAndRejectsAmbiguousDocuments() throws Exception {
    var mapper = JsonSourcePolicy.apply(new ObjectMapper());
    assertThrows(IOException.class, () -> mapper.readTree("{\"a\":1,\"a\":2}"));
    assertThrows(IOException.class, () -> mapper.readTree("{} {}"));
    var source = mapper.readTree("{\"n\":9007199254740993,\"d\":0.10000000000000001}");
    assertEquals("9007199254740993", source.get("n").asText());
    assertEquals("0.10000000000000001", source.get("d").asText());
    assertEquals(source, mapper.readTree(mapper.writeValueAsString(source)));
  }

  @Test void preservesTimestampPrecisionAndRequiresARealCalendarAndOffset() {
    String value = "0001-01-02T03:04:05.123456789-04:00";
    assertEquals(value, ProvenanceTimestamp.format(ProvenanceTimestamp.parse(value)));
    assertEquals("2026-01-02T03:04:05+01:00",
        ProvenanceTimestamp.format(ProvenanceTimestamp.parse("2026-01-02T03:04:05+0100")));
    for (String invalid : new String[] {"2026-02-30T00:00:00Z", "2026-01-02T03:04:05", "2026-01-02T24:00:00Z"})
      assertThrows(DateTimeParseException.class, () -> ProvenanceTimestamp.parse(invalid));
  }
}
