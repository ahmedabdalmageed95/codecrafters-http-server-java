import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
	ServerSocket serverSocket;
	
	public void execute() throws IOException {
		ServerSocket serverSocket = new ServerSocket(4221);
	    Socket socket=initiateNewConnection(serverSocket);
	    String requestString=recieveRequest(socket);
	    String url=getUrlFromRequestString(requestString);
	    sendResponse(socket,url);
	    serverSocket.close();
	}
	 
	  private  Socket initiateNewConnection(ServerSocket serverSocket) throws IOException {
		// Since the tester restarts your program quite often, setting SO_REUSEADDR
	      // ensures that we don't run into 'Address already in use' errors
	      serverSocket.setReuseAddress(true);    
	      System.out.println("accepted new connection");
	      return serverSocket.accept(); // Wait for connection from client.
	  }
	  
	  private String recieveRequest(Socket socket) throws IOException {
		  InputStream inputStream= socket.getInputStream();
		  byte [] requestToBytes= new byte[2048];
		  Integer requestByteCount= inputStream.read(requestToBytes);
		  return new String(requestToBytes, 0,requestByteCount, StandardCharsets.UTF_8);
	  }
	  
	  private String getUrlFromRequestString(String requestString) {
		  String[] requestLines=requestString.split(" ");
		  return requestLines[1];
	  }
	  
	  private  void sendResponse(Socket socket,String url) throws IOException {
		  OutputStream outpustStream=socket.getOutputStream();
	      byte[] responseToBytes=getResponse(url).getBytes(StandardCharsets.UTF_8);
	      outpustStream.write(responseToBytes);
	      outpustStream.flush();
	  }
	  
	  private  String getResponse(String url) {
		  return "HTTP/1.1 "+ getResponseCodeAndMessage(url)+getResponseBodyIfExists(url);
	  }
	  
	  private String getResponseCodeAndMessage(String url) {
		  return url.contentEquals("/") || url.startsWith("/echo/")?"200 OK":"404 Not Found";
	  }
	  
	  private String getResponseBodyIfExists(String url) {
		  final String crlf="\r\n\r\n";
		  return url.startsWith("/echo/")?getResponseBody(url):crlf;
	  }
	  
	  private String getResponseBody(String url) {
		  String [] urlParts=url.split("/");
		  String urlString=urlParts[urlParts.length-1];
		  Integer urlStringLength=urlString.length();
		  return "\r\nContent-Type: text/plain\r\nContent-Length: "+urlStringLength+"\r\n\r\n"+urlString;
	  }
}
