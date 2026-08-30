package org.metadatacenter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Comparator;
import java.util.Objects;

public class ResourceVersion implements Comparable<ResourceVersion> {

  private String value;
  private boolean valid;
  private Integer major;
  private Integer minor;
  private Integer patch;

  public static ResourceVersion ZERO_ZERO_ONE = ResourceVersion.forValue("0.0.1");

  private ResourceVersion(String value, boolean validate) {
    this.value = value;
    this.valid = false;
    if (validate) {
      validate();
    }
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @JsonCreator
  public static ResourceVersion forValue(String value) {
    return ResourceVersion.forValueWithoutValidation(value);
  }

  public void validate() {
    if (value != null) {
      String[] split = value.split("\\.");
      if (split.length == 3) {
        major = getInteger(split[0]);
        minor = getInteger(split[1]);
        patch = getInteger(split[2]);
        if (major != null && minor != null && patch != null) {
          if (major > 0 || minor > 0 || patch > 0) {
            valid = true;
          }
        }
      }
    }
  }

  public boolean isValid() {
    return valid;
  }

  public static ResourceVersion forValueWithValidation(String value) {
    return new ResourceVersion(value, true);
  }

  public static ResourceVersion forValueWithoutValidation(String value) {
    return new ResourceVersion(value, false);
  }

  private static Integer getInteger(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  @Override
  public int compareTo(ResourceVersion o) {
    if (o == null) {
      return 1;
    }
    if (this.valid != o.valid) {
      return this.valid ? 1 : -1;
    }
    if (!this.valid) {
      return Comparator.nullsFirst(String::compareTo).compare(this.value, o.value);
    }
    int majorComparison = Integer.compare(this.major, o.major);
    if (majorComparison != 0) {
      return majorComparison;
    }
    int minorComparison = Integer.compare(this.minor, o.minor);
    if (minorComparison != 0) {
      return minorComparison;
    }
    return Integer.compare(this.patch, o.patch);
  }

  public boolean isBefore(ResourceVersion o) {
    return this.compareTo(o) < 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof ResourceVersion other)) {
      return false;
    }
    return this.valid == other.valid && Objects.equals(this.value, other.value) &&
        Objects.equals(this.major, other.major) && Objects.equals(this.minor, other.minor) &&
        Objects.equals(this.patch, other.patch);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, valid, major, minor, patch);
  }

  public ResourceVersion nextMinorVersion() {
    if (!this.isValid()) {
      return ResourceVersion.ZERO_ZERO_ONE;
    }

    int nextMinor = this.minor + 1;
    return ResourceVersion.forValueWithValidation(
        this.major + "." + nextMinor + ".0"
    );
  }

  public ResourceVersion nextPatchVersion() {
    if (!this.isValid()) {
      return ResourceVersion.ZERO_ZERO_ONE;
    }

    int nextPatch = this.patch + 1;
    return ResourceVersion.forValueWithValidation(
        this.major + "." + this.minor + "." + nextPatch
    );
  }
}
