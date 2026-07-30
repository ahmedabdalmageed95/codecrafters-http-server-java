package handlers;

import java.util.Arrays;

public class UserAgentHandler extends AbstractHandler {

	public UserAgentHandler(String request) {
		super("/user-agent",request);
	}

	@Override
	public String getResponse() {
		return getResponseStatusLine()+" "+getResponseHeaders()+getResponseBody();
	}
	
	 protected String getResponseHeaders() {
		  Integer urlStringLength=getResponseBody().length();
		  return "\r\nContent-Type: text/plain\r\nContent-Length: "+urlStringLength+"\r\n\r\n";
	  }
	
	private String getResponseBody() {
		//User-Agent: foobar/1.2.3 -> foobar/1.2.3
		System.out.println(Arrays.asList(request.split("\r\n")).stream().filter(e->e.toLowerCase().contains("user-agent")).findFirst().get()
				);
		return Arrays.asList(request.split("\r\n")).stream().filter(e->e.toLowerCase().contains("user-agent")).findFirst().get()
				.split(":")[1].strip();
	}

}
