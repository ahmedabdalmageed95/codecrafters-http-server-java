package handlers;

public class EchoHandler extends AbstractHandler {

	public EchoHandler(String request) {
		super("/echo/",request);
	}
	
	public String getResponse() {
		return getResponseStatusLine()+getResponseHeaders()+getResponseBody();
	}
	 protected String getResponseHeaders() {
		  Integer urlStringLength=getResponseBody().length();
		  return "\r\nContent-Type: text/plain\r\nContent-Length: "+urlStringLength+"\r\n\r\n";
	  }
	 
	 
	 
	 protected String getResponseBody() {
		 String [] urlParts=getUrlParts();
		 return urlParts[urlParts.length-1];
	 }
	 
	 private String[] getUrlParts() {
		 String urlFromRequest=getUrlFromRequestString();
		 return urlFromRequest.split("/");
	 }
	 
}
