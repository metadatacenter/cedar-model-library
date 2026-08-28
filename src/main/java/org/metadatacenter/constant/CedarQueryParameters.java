package org.metadatacenter.constant;

public final class CedarQueryParameters {

  private CedarQueryParameters() {
  }

  public static final String QP_FOLDER_ID = "folder_id";
  public static final String QP_DEBUG = "debug";
  public static final String QP_RESOURCE_TYPES = "resource_types";
  public static final String QP_RESOURCE_TYPE = "resource_type";
  public static final String QP_SORT = "sort";
  public static final String QP_LIMIT = "limit";
  public static final String QP_OFFSET = "offset";
  public static final String QP_VERSION = "version";
  public static final String QP_PUBLICATION_STATUS = "publication_status";
  public static final String QP_SUMMARY = "summary";
  public static final String QP_FIELD_NAMES = "field_names";
  public static final String QP_IS_BASED_ON = "is_based_on";
  public static final String QP_Q = "q";
  public static final String QP_ID = "id";
  public static final String QP_SHARING = "sharing";
  public static final String QP_MODE = "mode";
  public static final String QP_FORMAT = "format";
  public static final String QP_NOTIFICATION_STATUS = "notification_status";
  public static final String QP_CATEGORY_ID = "category_id";
  public static final String QP_FOLDER_NAME = "folder_name";
  public static final String QP_SKIP_VALIDATION = "skip_validation";
  // Replace an existing artifact with exactly the document supplied: no provenance stamped, no child
  // identifier minted. Validation still applies, so the stored document is the validated one.
  public static final String QP_VERBATIM = "verbatim";

  public static final String QP_SOURCE_ARTIFACT_ID = "source_artifact_id";

}
