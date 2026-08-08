package http;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
	ServerSocket serverSocket;
	private HttpServerConfig httpServerConfig;
	public HttpServer(HttpServerConfig httpServerConfig) {
		this.httpServerConfig=httpServerConfig;
	}

	public void execute() throws IOException {
		ServerSocket serverSocket = new ServerSocket(httpServerConfig.getPort());
		while(true) {
			Socket socket=initiateNewConnection(serverSocket);
			Thread clientThread=new Thread(()->handleClient(socket));
			clientThread.start();
			
		    
		}
		// serverSocket.close();
	    
	}
	 
	private void handleClient(Socket socket) {
		try(socket){
		String requestString=recieveRequest(socket);
	    sendResponse(socket,requestString);
		}
		catch(IOException exception) {
			System.out.println(exception.getMessage());
		}
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
		  byte [] requestToBytes= new byte[4096];
		  Integer requestByteCount= inputStream.read(requestToBytes);
		  requestByteCount=requestByteCount==-1?4095:requestByteCount;
		  return new String(requestToBytes, 0,requestByteCount, StandardCharsets.UTF_8);
	  }
	  
	  
	  private  void sendResponse(Socket socket,String requestString) throws IOException {
		  OutputStream outpustStream=socket.getOutputStream();
	      byte[] responseToBytes=getHttpRouter(requestString).getResponse().getBytes(StandardCharsets.UTF_8);
	      outpustStream.write(responseToBytes);
	      outpustStream.flush();
	  }
	  
	  private HttpRouter getHttpRouter (String requestString) {
		  return httpServerConfig.getDirectory().isPresent()?
				  new HttpRouter(requestString,httpServerConfig.getDirectory().get())
				 :new HttpRouter(requestString);
	  }
	  
}
