# Low-level HTTP Protocol Support

## Design Goals

* A thin HTTP layer that encapsulates different transports, such as HTTP/1.1, HTTP/2, QUIC etc.

* APIs defined for this package are expected to be used by application-level frameworks or proxies.

* This package should expose a minimum set of transport-agnostic protocol semantics;
and rely on a higher-level framework etc to provide the necessary runtime support
for applications that choose to consume HTTP traffic directly.

* The set of core HTTP protocol semantics that require transport mapping is
being discussed at IETF. APIs defined in this package may follow the same model.