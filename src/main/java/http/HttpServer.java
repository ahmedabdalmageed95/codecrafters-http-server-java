package http;
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
	    sendResponse(socket,requestString);
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
	  
	  
	  private  void sendResponse(Socket socket,String requestString) throws IOException {
		  OutputStream outpustStream=socket.getOutputStream();
	      byte[] responseToBytes=new HttpRouter(requestString).getResponse().getBytes(StandardCharsets.UTF_8);
	      outpustStream.write(responseToBytes);
	      outpustStream.flush();
	  }
	  
}
