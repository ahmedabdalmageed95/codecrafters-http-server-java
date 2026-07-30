package http;
import java.util.Arrays;
import java.util.List;

import handlers.*;

public class HttpRouter {
	List<AbstractHandler> handlers;
	public HttpRouter(String request) {
		handlers=Arrays.asList(new EchoHandler(request),new RootHandler(request), new UserAgentHandler(request));
	}
	
	
	
	public String getResponse() {
		for(AbstractHandler handler : handlers) {
			if(handler.isUrlMatchingUrlFromRequest()) {
				return handler.getResponse(); 
			}
		}
		
		return new NotFoundHandler().getResponse();
	}
	
}
