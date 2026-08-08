package http;
import java.util.List;

import handlers.*;

public class HttpRouter {
	List<AbstractHandler> handlers;
	public HttpRouter(List<AbstractHandler> handlers) {
		this.handlers=handlers;
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
