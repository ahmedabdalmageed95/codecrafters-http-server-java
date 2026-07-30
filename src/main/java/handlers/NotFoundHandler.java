package handlers;
import http.HttpStatusCode;
public class NotFoundHandler {
	public String getResponse() {
		return "HTTP/1.1 "+HttpStatusCode.NOT_FOUND.getCode()+" "+HttpStatusCode.NOT_FOUND.getReasonPhrase()+"\r\n\r\n";
	}
	
}
