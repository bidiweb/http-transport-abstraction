package bidiweb.http;

/**
 * The <code>HttpTransactionHandler</code> is provided by the hosting runtime (framework, proxies)
 * to manage an HTTP transaction, including I/O and life-cycle events.
 *
 * Threading semantics:
 * 1. The handler should not block for any reason in response to an event
 * 2. The runtime should not deliver multiple events at the same time, i.e. whenever an existing
 *    event hasn't been completely handled
 * 3. The runtime should respect the strict ordering at the wire-level (as seen by the transport
 *    implementation).
 */
public abstract class HttpTransactionHandler {

  /**
   * The transaction has been closed (as completed) by the peer.
   */
  public abstract void onClose();

  /**
   * The transaction has been aborted by the peer or the runtime.
   */
  public abstract void onAbort();

  /**
   * Initial response headers received.
   * @param headers The initial response headers
   */
  public void onInitialHeaders(RawResponseHeaders headers) {
    // we don't support client-side transactions yet
  }

  /**
   * Extra headers received (e.g. as trailers)
   * @param headers The headers
   */
  public abstract void onMoreHeaders(RawHttpHeaders headers);

  /**
   * New metadata received.
   *
   * @param metaData The metadata
   */
  public abstract void onMetadata(TransportMetaData metaData);

  /**
   * The body is readable, following a {@link HttpTransaction#readMoreBody()} operation.
   *
   * This respects the TCP style flow-control.
   */
  public abstract void onReadable();

  /**
   * The body is writable, following a {@link HttpTransaction#writeMoreBody()} operation.
   *
   * This respects the TCP style flow-control.
   */
  public abstract void onWritable();
}
