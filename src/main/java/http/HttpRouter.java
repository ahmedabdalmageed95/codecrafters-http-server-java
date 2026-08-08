package http;
import java.util.Arrays;
import java.util.List;

import handlers.*;

public class HttpRouter {
	List<AbstractHandler> handlers;
	public HttpRouter(String request) {
		handlers=Arrays.asList(new RootHandler(request), new EchoHandler(request), new UserAgentHandler(request));
	}
	
	public HttpRouter(String request,String directory) {
		this(request);
		handlers.add(new FilesHandler(request,directory));
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
