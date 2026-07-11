import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
	ServerSocket serverSocket;
	
	public void execute() throws IOException {
		ServerSocket serverSocket = new ServerSocket(4221);
	    Socket socket=initiateNewConnection(serverSocket);
	    sendResponse(socket);
	    serverSocket.close();
	}
	 
	  private static Socket initiateNewConnection(ServerSocket serverSocket) throws IOException {
		// Since the tester restarts your program quite often, setting SO_REUSEADDR
	      // ensures that we don't run into 'Address already in use' errors
	      serverSocket.setReuseAddress(true);    
	      System.out.println("accepted new connection");
	      return serverSocket.accept(); // Wait for connection from client.
	  }
	  
	  private static void sendResponse(Socket socket) throws IOException {
		  OutputStream outpustStream=socket.getOutputStream();
	      byte[] responseToBytes=getResponse().getBytes(StandardCharsets.UTF_8);
	      outpustStream.write(responseToBytes);
	      outpustStream.flush();
	  }
	  
	  private static String getResponse() {
		  return "HTTP/1.1 200 OK\r\n\r\n";
	  }
}
