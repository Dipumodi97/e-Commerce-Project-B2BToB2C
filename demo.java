E-commerce Application
  =======================

  Zipkin + Sleuth
  Distributed Loggging

  Q)what is Distributed Loggging?
  =================================
  Ans-> 
  Each Service has it's own Separate log file
  If order fails ,identifying the failure point is difficult
  Tracking a single request across multiple microservices
  using one common identity for the entire request.
  Every incoming request gets a unique identity.
  Enables end to end request visibility
  Helps identity latency and failure points

  Q) WHAT IS SPRING CLOUD SLEUTH?
  =====================================
  Ans->
  Library for distributed tracing in Spring boot
  Automatically assigns identity to requests 
  Generate Trace Id
  Generate Span Id
  Propagate IDs across services
  Inject IDs into  logs
  Send tracing data to Zipkin

  Q)TRACING ID GENERATION
  -=======================
  Triggered when a new Request arrived 
  Works for Http, kafka ,and Async calls
  New External request gets a new Trace Id(Randomly generate unique Hexadecimal id)
  Internal services requests reuse existing Trace ID.

  Q)SPAN ID GENERATION
  =========================
  Span represents a single service Operation
  Each Service creates It's own span
  Parent-child relationships between Spans
  Captures Execution start and end time

  Q)Context Propagation
  ========================
  Trace and Span IDs are sent via Request  Headers
  Works automatically with RestTemplate and webClient
  Next service continues the same trace
  No Manual header handling required

  Q)Log Correlation
  ==================
  Sleuth stores Trace and Span IDs in MDC(Map Diagonistic Context)
  Logging Frameworks read MDC Value.
  Every Log lines contains Trace ID
  Makes log correlation extremely easy

  Q) SENDING DATA TO ZIPKIN
  =============================
  Span data is sent after span completion
  Sent asynchronously in background
  Application performance is not affected
  Data includes  timing,service name,  errors

  Q) WHAT IS ZIPKIN?
  =======================
  Distributed tracing System
  Tracks full lifecycle of a request
  Visualizes request flow across services
  Helps detect latency and failures
  Does not generate Trace or Span IDs
  Aggregates data from all Microservices.
  Groups spans by Trace IDs
  Built Parent-Child  realtionsips
  Zipkin UI Visualization
