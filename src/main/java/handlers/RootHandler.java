package handlers;

public class RootHandler extends AbstractHandler {
	
	
	
	public RootHandler(String request) {
		super("/",request);
	}

	@Override
	public boolean areConditionsMatched() {
		return urlToHandle.contentEquals(getUrlFromRequestString());
	}

	@Override
	public String getResponse() {
		return getResponseStatusLine()+"\r\n\r\n";
	}
}
