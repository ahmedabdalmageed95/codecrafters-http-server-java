package handlers;

public class RootHandler extends AbstractHandler {
	
	
	
	public RootHandler(String request) {
		super("/",request);
	}

	public boolean isUrlMatching(String urlFromRequest) {
		return getUrlFromRequestString().contentEquals(urlFromRequest.toLowerCase());
	}

	@Override
	public String getResponse() {
		return getResponseStatusLine()+"\r\n\r\n";
	}
}
