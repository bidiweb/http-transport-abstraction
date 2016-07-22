package bidiweb.http;

import javax.annotation.concurrent.Immutable;

/**
 * This class defines connection-level state as decided by the transport.
 *
 * During the life cycle of an <code>HttpTransaction</code>, this is an immutable object.
 *
 * Example data includes: PeerSecurityInfo, connectionId and some may overlap with metadata.
 */
@Immutable
public abstract class ConnectionInfo {
  /**
   * Existing & future transports.
   */
  enum TransportType {
    HTTP1,
    HTTP2,
    QUIC
  }

  private final TransportType transportType;

  /**
   * @param transportType The transport type
   */
  public ConnectionInfo(TransportType transportType) {
    this.transportType = transportType;
  }

  /**
   * Returns the transport type for the connection
   */
  public abstract TransportType getTransportType();
}
