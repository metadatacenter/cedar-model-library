package org.metadatacenter.model.policy;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Apply raw JSON rules before typed or artifact validation can lose source information. */
public final class JsonSourcePolicy {
  private JsonSourcePolicy() {}
  public static ObjectMapper apply(ObjectMapper mapper) {
    mapper.enable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
    mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
    return mapper;
  }
}
