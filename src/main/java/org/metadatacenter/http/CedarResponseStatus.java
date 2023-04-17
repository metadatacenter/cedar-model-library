package org.metadatacenter.http;

public enum CedarResponseStatus {
  OK(200, "OK"),
  CREATED(201, "Created"),
  NO_CONTENT(204, "No Content"),

  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
  NOT_ACCEPTABLE(406, "Not Acceptable"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
  NOT_IMPLEMENTED(501, "Not Implemented"),
  BAD_GATEWAY(502, "Bad Gateway"),
  SERVICE_UNAVAILABLE(503, "Service Unavailable"),
  HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported");

  private final int code;
  private final String reason;

  private CedarResponseStatus(int statusCode, String reasonPhrase) {
    this.code = statusCode;
    this.reason = reasonPhrase;
  }

  public int getStatusCode() {
    return code;
  }

  public static CedarResponseStatus fromStatusCode(int code) {
    for (CedarResponseStatus s : values()) {
      if (s.getStatusCode() == code) {
        return s;
      }
    }
    return null;
  }
}
