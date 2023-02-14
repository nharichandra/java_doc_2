
https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-fn
Refer the above link for explantion

HandlerFunctions ----@RequestMapping.

Incoming requests are routed to handler functions with a RouterFunction, which is a function that takes a ServerRequest, and returns a Mono<HandlerFunction>. If a request matches a particular route, a handler function is returned; otherwise it returns an empty Mono. The RouterFunction has a similar purpose as the @RequestMapping annotation in @Controller classes.
	@FunctionalInterface
	public interface HandlerFunction<T extends ServerResponse> {
		Mono<T> handle(ServerRequest request);
	}
	
		 
RouterFunctions

Incoming HTTP requests are handled by a HandlerFunction, which is essentially a function that takes a ServerRequest and returns a Mono<ServerResponse>. The annotation counterpart to a handler function would be a method with @RequestMapping.
		@FunctionalInterface
		public interface RouterFunction<T extends ServerResponse> {
			Mono<HandlerFunction<T>> route(ServerRequest request);
			// ...
		}
		 


For Webflux application with webclient refer
https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
 

	