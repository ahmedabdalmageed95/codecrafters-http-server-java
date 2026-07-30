import java.io.IOException;

import http.HttpServer;


public class Main {
  public static void main(String[] args) {

     try {
       HttpServer httpServer =new HttpServer();
       
       httpServer.execute();
     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     }
  }
  
 
}
