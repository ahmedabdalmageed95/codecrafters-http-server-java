package http;
import java.util.Arrays;
import java.util.List;

public class HttpResponse {
	private String url;
	public HttpResponse(String url) {
		this.url=url;
	}
	
	  public  String getResponse() {
		  return "HTTP/1.1 "+ getResponseStatusLine()+getResponseBodyIfExists();
	  }
	  
	  private String getResponseStatusLine() {
		  return url.contentEquals("/") || isValidEndPoint()?"200 OK":"404 Not Found";
	  }
	  
	  private String getResponseBodyIfExists() {
		  final String crlf="\r\n\r\n";
		  return isValidEndPoint()?getEchoEndpointResponseBody():crlf;
	  }
	  
	  private String getEchoEndpointResponseBody() {
		  String [] urlParts=url.split("/");
		  String urlString=urlParts[urlParts.length-1];
		  Integer urlStringLength=urlString.length();
		  return "\r\nContent-Type: text/plain\r\nContent-Length: "+urlStringLength+"\r\n\r\n"+urlString;
	  }
	  
	  private Boolean isValidEndPoint() {
		  List<String> validEndpoints=Arrays.asList("/echo/","/user-agent");
		  return validEndpoints.stream().anyMatch(endPoint->url.startsWith(endPoint));
	  }
}
