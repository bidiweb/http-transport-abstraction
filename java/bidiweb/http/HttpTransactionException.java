package bidiweb.http;

/**
 * Indicates failure conditions that typically abort a transaction.
 */
public class HttpTransactionException extends Exception {
  /**
   * Constructs a new instance with the specified message and root cause.
   *
   * @param message The failure information
   * @param cause The cause exception
   */
  public HttpTransactionException(String message, Throwable cause) {
    super(message, cause);
  }
}
