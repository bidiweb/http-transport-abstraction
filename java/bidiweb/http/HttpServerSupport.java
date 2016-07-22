package bidiweb.http;

/**
 * Server-side support for handling HTTP transactions.
 *
 * There is no plan to create a shared server runtime here. Each transport-specific implementation
 * should have its own bootstrapping and handle its own runtime/compile-time dependencies,
 * as well as port-sharing logic.
 *
 * Note connection-level handshake is out of the scope; and should be addressed
 * as part of the underlying transport implementation itself.
 */
public abstract class HttpServerSupport {

  /**
   * The handler interface for accepting a new transaction.
   */
  public interface ServerHandler {

    /**
     * @param newTransaction The new transaction, with all the initial request headers
     * @return the transaction handler if the request is to be handled
     * @throws HttpTransactionException if the transaction is not to be handled due to
     * error conditions. The underlying transport should abort (as discard) the transaction
     * immediately.
     */
    HttpTransactionHandler doTransaction(HttpTransaction newTransaction)
        throws HttpTransactionException;
  }

  /**
   * Set the handler.
   *
   * @param handler the handler
   */
  public abstract void setHandler(ServerHandler handler);

  /**
   * A template method to initialize the transport runtime.
   */
  public abstract void initialize() throws RuntimeException;

  /**
   * A template method to start up the runtime, to accept new transactions.
   */
  public abstract void startup() throws RuntimeException;

  /**
   * A template method to shut down the runtime.
   */
  public abstract void shutdown() throws RuntimeException;
}
