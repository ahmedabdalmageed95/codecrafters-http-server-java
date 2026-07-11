import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {
  public static void main(String[] args) {
    // You can use print statements as follows for debugging, they'll be visible when running tests.
    System.out.println("Logs from your program will appear here!");

    // TODO: Uncomment the code below to pass the first stage
    
     try {
       ServerSocket serverSocket = new ServerSocket(4221);
    
       // Since the tester restarts your program quite often, setting SO_REUSEADDR
       // ensures that we don't run into 'Address already in use' errors
       serverSocket.setReuseAddress(true);    
       Socket socket=serverSocket.accept(); // Wait for connection from client.
       System.out.println("accepted new connection");
       OutputStream outpustStream=socket.getOutputStream();
       byte[] responseToBytes=getResponse().getBytes(StandardCharsets.UTF_8);
       outpustStream.write(responseToBytes);
       outpustStream.flush();
       serverSocket.close();
       
     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     }
  }
  
  private static String getResponse() {
	  return "HTTP/1.1 200 OK\r\n\r\n";
  }
}
