package org.metadatacenter.constant;

import org.metadatacenter.http.CedarResponseStatus;

public final class HttpConstants {


  private HttpConstants() {
  }

  // HTTP headers
  public static final String HTTP_HEADER_LINK = "Link";
  public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
  public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";
  public static final String HTTP_HEADER_ACCEPT = "Accept";
  public static final String HTTP_HEADER_CONTENT_DISPOSITION = "Content-Disposition";
  public static final String HTTP_HEADER_ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";


  // HTTP header prefix
  public static final String HTTP_AUTH_HEADER_BEARER_PREFIX = "Bearer ";
  public static final String HTTP_AUTH_HEADER_APIKEY_PREFIX = "apiKey ";

  /**
   * The challenge every 401 carries, naming both schemes {@code Authorization} accepts above.
   *
   * <p>RFC 9110 requires a 401 to send {@code WWW-Authenticate}, and a client that receives none has no
   * way to discover which scheme to use. Each challenge carries a realm so the comma separates two
   * challenges rather than reading as an auth-param of the first.
   */
  public static final String HTTP_AUTH_CHALLENGE = "Bearer realm=\"CEDAR\", apiKey realm=\"CEDAR\"";

  // HTTP Link header types
  public static final String HEADER_LINK_TYPE_FIRST = "first";
  public static final String HEADER_LINK_TYPE_LAST = "last";
  public static final String HEADER_LINK_TYPE_PREV = "prev";
  public static final String HEADER_LINK_TYPE_NEXT = "next";

  // Content Types
  public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
  public static final String CONTENT_TYPE_APPLICATION_MERGE_PATCH_JSON = "application/merge-patch+json";
  public static final String CONTENT_TYPE_APPLICATION_YAML = "application/x-yaml";
  public static final String CONTENT_TYPE_APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";


  // StatusCodes

  public static final int OK = CedarResponseStatus.OK.getStatusCode();
  public static final int CREATED = CedarResponseStatus.CREATED.getStatusCode();
  public static final int NO_CONTENT = CedarResponseStatus.NO_CONTENT.getStatusCode();
  public static final int BAD_REQUEST = CedarResponseStatus.BAD_REQUEST.getStatusCode();
  public static final int FORBIDDEN = CedarResponseStatus.FORBIDDEN.getStatusCode();
  public static final int NOT_FOUND = CedarResponseStatus.NOT_FOUND.getStatusCode();
  public static final int METHOD_NOT_ALLOWED = CedarResponseStatus.METHOD_NOT_ALLOWED.getStatusCode();

}
