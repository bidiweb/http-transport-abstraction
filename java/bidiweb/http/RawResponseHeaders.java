package bidiweb.http;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Extending <code>RawHttpHeaders</code> with response specific data.
 */
@ThreadSafe
public abstract class RawResponseHeaders extends RawHttpHeaders {

  /**
   * Returns the status code
   */
  public abstract int getStatus();

  /**
   * Returns the status text, if any
   */
  public abstract String getStatusText();
}
