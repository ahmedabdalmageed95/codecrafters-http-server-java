package http;

public enum HttpStatusCode {
	OK(200, "OK"),
	NOT_FOUND(404, "Not Found");
	
	private final int code;
    private final String reasonPhrase;

    HttpStatusCode(int code, String reasonPhrase) {
        this.code = code;
        this.reasonPhrase = reasonPhrase;
    }

    public int getCode() {
        return code;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
