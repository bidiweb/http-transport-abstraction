package bidiweb.http;

import javax.annotation.concurrent.ThreadSafe;

/**
 * This class represents a request-response transaction as supported by the underlying
 * transport. A transaction is started after the initial request headers have been generated.
 *
 * This class may be used to represent an HTTP transaction on both the client and server side,
 * although the current implementation is mainly for the server-side (or as a proxy).
 *
 * All the operations defined in this class are non-blocking operations.
 *
 * TODO: keep-alive, 100-continue, server-push, priorities
 */
@ThreadSafe
public abstract class HttpTransaction {

  /**
   * The initial request headers (that creates a new transaction).
   */
  protected final RawRequestHeaders initialRequestHeaders;

  /**
   * The connection information.
   */
  protected final ConnectionInfo connectionInfo;

  /**
   * @param initialRequestHeaders The request headers that precedes the body
   */
  public HttpTransaction(RawRequestHeaders initialRequestHeaders, ConnectionInfo connectionInfo) {
    this.initialRequestHeaders = initialRequestHeaders;
    this.connectionInfo = connectionInfo;
  }

  /**
   * Returns the initial request headers
   */
  public RawRequestHeaders getInitialRequestHeaders() {
    return this.initialRequestHeaders;
  }

  /**
   * Returns the connection info.
   */
  public ConnectionInfo getConnectionInfo() {
    return this.connectionInfo;
  }

  /**
   * Sends the initial response headers to the client. This effectively "commits" the status
   * of a response.
   */
  public abstract void sendInitialHeaders(RawResponseHeaders headers);

  /**
   * Sends the initial request headers to the server.
   */
  public void sendInitialHeaders() {
    // we don't support client-side transactions yet
  }

  /**
   * Sends more headers to the peer, e.g. trailers.
   * @param headers The headers
   */
  public abstract void sendMoreHeaders(RawHttpHeaders headers);

  /**
   * Sends metadata to the peer.
   * @param metaData The metaData
   */
  public abstract void sendMetadata(TransportMetaData metaData);

  /**
   * Half-close the transaction.
   */
  public abstract void close();

  /**
   * Aborts the transaction due to error conditions. Some transports may be able to propagate
   * an abort as an explicit cancellation to the peer.
   */
  public abstract void abort();

  /**
   * Notifies the <code>HttpTransactionHandler</code> (possibly from the same stack)
   * whenever there are more bytes to read.
   *
   * Duplicated calls to this method, i.e. before the handler is invoked, may cause
   * undefined behavior.
   */
  public abstract void readMoreBody();

  /**
   * Notifies the <code>HttpTransactionHandler</code> (possibly from the same stack)
   * whenever more bytes may be written to the transport.
   *
   * Duplicated calls to this method, i.e. before the handler is invoked, may cause
   * undefined behavior.
   */
  public abstract void writeMoreBody();

  /**
   * Reads available bytes from the body, by writing to the specified destination
   * buffer, starting at the specified offset, up to the specified number of bytes.
   *
   * This should only be called from the <code>HttpTransactionHandler</code>.
   *
   * @param buffer The destination buffer
   * @param offset The offset of the buffer
   * @param length The maximum number of bytes
   * @return the number of bytes read from the transport.
   */
  public abstract int read(byte[] buffer, int offset, int length);

  /**
   * Writes to the body, by reading from the specified source buffer, starting at
   * the specified offset, up to the specified number of bytes.
   *
   * This should only be called from the <code>HttpTransactionHandler</code>.
   *
   * @param buffer The source buffer
   * @param offset The offset of the buffer
   * @param length The maximum number of bytes
   * @return the number of bytes written to the transport.
   */
  public abstract int write(byte[] buffer, int offset, int length);

  /**
   * Enables direct I/O proxying when supported by the underlying transports. This should only
   * be enabled for request or response body (byte-stream), before the body has been read or
   * written.
   *
   * @param peerRequest The other transaction to peer with, on either side of the proxy. Note
   * a transaction may represent either a request or a response; and forwardBody should be
   * called only once on either transaction.
   * @throws HttpTransactionException if forwarding is not supported by the underlying transports
   * or either request is in an invalid state to enable forwarding.
   */
  public abstract void forwardBody(HttpTransaction peerRequest) throws HttpTransactionException;
}
