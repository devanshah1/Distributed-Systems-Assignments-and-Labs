import java.net.*;
import java.io.*;
public class OrderServer {
  public static void main (String args[]) {
                try{
                        int serverPort = 9876;
                        ServerSocket listenSocket = new ServerSocket(serverPort);
                        while(true) {
                                Socket clientSocket = listenSocket.accept();
                                Connection c = new Connection(clientSocket);
                        }
                } catch(IOException e){
                                System.out.println("Listen :"+e.getMessage());}
  }
}

class Connection extends Thread {
    ObjectInputStream in;
        ObjectOutputStream out;
        Socket clientSocket;
        public Connection (Socket aClientSocket) {
          try {
            clientSocket = aClientSocket;
            in = new ObjectInputStream( clientSocket.getInputStream());
            out =new ObjectOutputStream( clientSocket.getOutputStream());
            this.start();
            } catch(IOException e)  {
                System.out.println("Connection:"+e.getMessage());}
        }
        public void run(){
          try {                                  // an echo server
			Order ord = (Order) in.readObject();
			System.out.println("Received: " + ord.name);
			ord.shipping.streetNum = 9999;
			ord.shipping.street = "Queen St.";
			out.writeObject(ord);
            }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
                try {
                        clientSocket.close();}catch (IOException e){/*close failed*/}}
        }
}
