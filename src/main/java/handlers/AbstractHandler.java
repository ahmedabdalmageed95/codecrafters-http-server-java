package handlers;
import http.HttpStatusCode;
public abstract class AbstractHandler {

	protected String urlToHandle;
	protected String request;
	protected AbstractHandler(String urlToHandle,String request) {
		this.urlToHandle = urlToHandle;
		this.request=request;
	}
	
	protected String getUrlFromRequestString() {
		 String[] requestLines=request.split(" ");
		 return requestLines[1];
	 }
	
	
	public boolean isUrlMatchingUrlFromRequest() {
		System.out.println(urlToHandle);
		System.out.println(getUrlFromRequestString());
		return urlToHandle.startsWith(getUrlFromRequestString());
	}
	
	 protected  String getResponseStatusLine() {
		  return "HTTP/1.1 "+ HttpStatusCode.OK.getCode()+" "+HttpStatusCode.OK.getReasonPhrase();
	  }
	 abstract public String getResponse();
	 
	 
}
