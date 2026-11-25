package org.metadatacenter.constant;

public final class CedarQueryParameters {

  private CedarQueryParameters() {
  }

  public static final String QP_FOLDER_ID = "folder_id";
  public static final String QP_RESOURCE_TYPES = "resource_types";
  public static final String QP_RESOURCE_TYPE = "resource_type";
  public static final String QP_SORT = "sort";
  public static final String QP_LIMIT = "limit";
  public static final String QP_OFFSET = "offset";
  // Names a position in a result set rather than a distance into it: the deep search hands one back
  // with every page, and the caller passes it to ask for the next.
  public static final String QP_CONTINUATION = "continuation";
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

  // The draft a new version is taken from, when the caller names it rather than the server choosing.
  public static final String QP_DRAFT_ID = "draft_id";
  public static final String QP_COMPACT = "compact";
  public static final String QP_INPUT = "input";
  public static final String QP_TYPE = "type";
  public static final String QP_KIND = "kind";
  public static final String QP_STATE = "state";
  public static final String QP_LANG = "lang";
  public static final String QP_SCOPE = "scope";
  public static final String QP_SUGGEST = "suggest";
  public static final String QP_EXACT = "exact";
  public static final String QP_EXACT_MATCH = "exact_match";
  public static final String QP_SOURCE = "source";
  public static final String QP_SOURCES = "sources";
  public static final String QP_SOURCE_ACRONYM = "sourceAcronym";
  public static final String QP_COLLECTION = "collection";
  public static final String QP_VERSION_ID = "versionId";
  public static final String QP_TERM_IRI = "termIri";
  public static final String QP_PROPERTY_IRI = "propertyIri";
  public static final String QP_SUBTREE_ROOT_ID = "subtree_root_id";
  public static final String QP_URI = "uri";
  public static final String QP_MAX_DEPTH = "max_depth";
  public static final String QP_INCLUDE_DETAILS = "include_details";
  public static final String QP_REQUIRE_DEFINITIONS = "require_definitions";
  // A filter on a field's time is <field>_after, inclusive, and <field>_before, exclusive, each an
  // ISO-8601 instant. A window over the data itself is from, inclusive, and to, exclusive.
  public static final String QP_MODIFIED_AFTER = "modified_after";
  public static final String QP_MODIFIED_BEFORE = "modified_before";
  public static final String QP_FROM = "from";
  public static final String QP_TO = "to";
  // Bounds that are versions rather than times.
  public static final String QP_FROM_VERSION = "from_version";
  public static final String QP_TO_VERSION = "to_version";
  public static final String QP_TABLE = "table";
  public static final String QP_MAX_SPANS = "max_spans";
  public static final String QP_MIN_DURATION_MS = "min_duration_ms";

  // The withdrawn page-number paging. No route declares these: a request carrying one is refused.
  // Both spellings of the page size were accepted, so both are refused.
  public static final String QP_PAGE = "page";
  public static final String QP_PAGE_SIZE = "page_size";
  public static final String QP_PAGE_SIZE_CAMEL = "pageSize";

}
