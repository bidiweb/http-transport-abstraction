package bidiweb.http;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

/**
 * This should be implemented as a view object to the underlying transport-provided headers.
 *
 * The raw headers will be in different wire-specific formats. Direct access/copy of raw headers
 * may be supported in future.
 *
 * TODO: binary headers when supported
 */
@ThreadSafe
public abstract class RawHttpHeaders {

  /**
   * Returns the first header value for the given name. The actual value is not parsed in any way.
   *
   * @param name The header name
   * @return The header value or null
   */
  public abstract String getHeader(String name);

  /**
   * Returns a list of header value for the given name. The actual values are not parsed in any way.
   *
   * @param name The header name
   * @return The list of head values or an empty list
   */
  public abstract List<String> getHeaders(String name);

  /**
   * Returns all the header names as a list.
   *
   * @return the header names
   */
  public abstract List<String> getHeaderNames();

  /**
   * Adds (append semantics) the specified header.
   *
   * @param name The header name
   * @param value The header value
   */
  public abstract void addHeader(String name, String value);

  /**
   * Removes any headers under the specified header name.
   *
   * @param name The header name
   */
  public abstract void removeHeaders(String name);
}
