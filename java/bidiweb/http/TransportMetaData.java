package bidiweb.http;

import java.util.List;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Transport specific meta-data, whose semantics are not covered by HTTP headers.
 *
 * The format of TransportMetaData is transport specific.
 *
 * Metadata needs be sent/received atomically as a batch.
 */
@ThreadSafe
public abstract class TransportMetaData {

  /**
   * Gets metadata by the key.
   *
   * @param key The key
   * @return the value or null
   */
  public abstract Object getMetaData(Object key);

  /**
   * Adds metadata.
   *
   * @param key The key
   * @param value the value
   */
  public abstract void addMetaData(Object key, Object value);

  /**
   * Returns all the keys
   */
  public abstract List<Object> getMetaDataKeys();
}
