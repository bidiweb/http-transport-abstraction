package bidiweb.http;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Extending <code>RawHttpHeaders</code> with request specific data.
 */
@ThreadSafe
public abstract class RawRequestHeaders extends RawHttpHeaders {

  /**
   * Returns the Url path
   */
  public abstract String getUrl();

  /**
   * Returns the HTTP version string
   */
  public abstract String getVersion();

  /**
   * Returns the HTTP method string
   */
  public abstract String getMethod();
}
