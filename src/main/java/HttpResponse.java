
public class HttpResponse {
	private String url;
	public HttpResponse(String url) {
		this.url=url;
	}
	
	  public  String getResponse() {
		  return "HTTP/1.1 "+ getResponseCodeAndMessage()+getResponseBodyIfExists();
	  }
	  
	  private String getResponseCodeAndMessage() {
		  return url.contentEquals("/") || isEchoEndpoint()?"200 OK":"404 Not Found";
	  }
	  
	  private String getResponseBodyIfExists() {
		  final String crlf="\r\n\r\n";
		  return isEchoEndpoint()?getEchoEndpointResponseBody():crlf;
	  }
	  
	  private String getEchoEndpointResponseBody() {
		  String [] urlParts=url.split("/");
		  String urlString=urlParts[urlParts.length-1];
		  Integer urlStringLength=urlString.length();
		  return "\r\nContent-Type: text/plain\r\nContent-Length: "+urlStringLength+"\r\n\r\n"+urlString;
	  }
	  
	  private Boolean isEchoEndpoint() {
		  return url.startsWith("/echo/");
	  }
}
