package handlers;
import http.HttpStatusCode;
public class NotFoundHandler {
	public String getResponse() {
		return HttpStatusCode.NOT_FOUND.getCode()+" "+HttpStatusCode.NOT_FOUND.getReasonPhrase();
	}
	
}
