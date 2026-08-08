import java.io.IOException;

import http.HttpServer;
import http.HttpServerConfig;


public class Main {
  public static void main(String[] args) {

     try {
       HttpServerConfig httpServerConfig=new HttpServerConfig(args,4221);
       HttpServer httpServer =new HttpServer(httpServerConfig);
       httpServer.execute();
     } catch (IOException e) {
       System.out.println("IOException: " + e.getMessage());
     }
  }
  
 
}
