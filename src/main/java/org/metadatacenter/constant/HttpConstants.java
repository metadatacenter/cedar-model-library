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

  // HTTP header prefix
  public static final String HTTP_AUTH_HEADER_BEARER_PREFIX = "Bearer ";
  public static final String HTTP_AUTH_HEADER_APIKEY_PREFIX = "apiKey ";

  // HTTP Link header types
  public static final String HEADER_LINK_TYPE_FIRST = "first";
  public static final String HEADER_LINK_TYPE_LAST = "last";
  public static final String HEADER_LINK_TYPE_PREV = "prev";
  public static final String HEADER_LINK_TYPE_NEXT = "next";

  // Content Types
  public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
  public static final String CONTENT_TYPE_APPLICATION_MERGE_PATCH_JSON = "application/merge-patch+json";

  // StatusCodes

  public static final int OK = CedarResponseStatus.OK.getStatusCode();
  public static final int CREATED = CedarResponseStatus.CREATED.getStatusCode();
  public static final int NO_CONTENT = CedarResponseStatus.NO_CONTENT.getStatusCode();
  public static final int BAD_REQUEST = CedarResponseStatus.BAD_REQUEST.getStatusCode();
  public static final int FORBIDDEN = CedarResponseStatus.FORBIDDEN.getStatusCode();
  public static final int NOT_FOUND = CedarResponseStatus.NOT_FOUND.getStatusCode();
  public static final int METHOD_NOT_ALLOWED = CedarResponseStatus.METHOD_NOT_ALLOWED.getStatusCode();

}
