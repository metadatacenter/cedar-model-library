package org.metadatacenter.constant;

public final class HttpConnectionConstants {

  private HttpConnectionConstants() {
  }

  public static final int CONNECTION_TIMEOUT = 1000;

  /**
   * How long a caller waits for a connection out of the pool. Apache's own default is three
   * minutes, which turns a saturated dependency into exhausted worker threads: the wait happens
   * before the connect and response timeouts are consulted, so a hop nominally bounded at 21
   * seconds can hold a worker for 201. A second is deliberately short. A full pool means the
   * dependency is already saturated, and shedding the request is worth more than queueing it.
   */
  public static final int CONNECTION_LEASE_TIMEOUT = 1000;

  public static final int SOCKET_TIMEOUT = 20000;

  /**
   * The same three values for a call no user is waiting on. A bulk import, a reindex or a clone
   * over thousands of artifacts has minutes of legitimate work per call and no client whose
   * patience bounds it, so the interactive response timeout was not too generous for these but too
   * mean. The longer lease follows from the same reasoning: a job that waits is better than a job
   * that fails.
   */
  public static final int BATCH_CONNECTION_TIMEOUT = 3000;

  public static final int BATCH_CONNECTION_LEASE_TIMEOUT = 5000;

  public static final int BATCH_SOCKET_TIMEOUT = 120000;
}
