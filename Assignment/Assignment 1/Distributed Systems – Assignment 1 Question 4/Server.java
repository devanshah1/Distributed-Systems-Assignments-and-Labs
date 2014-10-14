import java.net.*;
import java.io.*;

/**
 * 
 * @author 100428864
 *
 */
public class Server
{
    public static void main ( String args [] )
    {   
        try
        {
            int serverPort = 7896 ;
            ServerSocket listenSocket = new ServerSocket ( serverPort ) ;
            
            while ( true )
            {
                Socket clientSocket = listenSocket.accept () ;
                Connection c = new Connection ( clientSocket ) ;
            }
        }
        catch ( IOException e ) { System.out.println ( "Listen :" + e.getMessage () ) ; }
    }
}

class Connection extends Thread
{
    ObjectInputStream  input ;
    ObjectOutputStream output ;
    Socket             clientSocket ;

    public Connection ( Socket aClientSocket )
    {
        try
        {
            clientSocket = aClientSocket ;
            input = new ObjectInputStream ( clientSocket.getInputStream () ) ;
            output = new ObjectOutputStream ( clientSocket.getOutputStream () ) ;
            this.start () ;
        }
        catch ( IOException e ) { System.out.println ( "Connection: " + e.getMessage () ) ; }
    }

    public void run ()
    {
        try
        {
            Couple c = ( Couple ) input.readObject () ;
            System.out.println ( "\n(Couple) Server Received:" ) ;
            System.out.println ( "\n  Person one\n" + c.one.toString() ) ;
            
            // print only when person 2 is null 
            if ( c.two == null ) 
            {
                System.out.println ( "\n  Person two" ) ;
                System.out.println ( "   Name: NULL" ) ;
                System.out.println ( "   Place: NULL" ) ;
                System.out.println ( "   Year: NULL" ) ;
            }
            
            Person p2 = new Person ( "Sandy", "Vaughan", 1992 ) ;
            c.two = p2 ;
            output.writeObject ( c ) ;

        }
        catch ( EOFException e ) { System.out.println ( "EOF:" + e.getMessage () ) ; }
        catch ( IOException e ) { System.out.println ( "IO:" + e.getMessage () ) ; }
        catch ( ClassNotFoundException e ) { e.printStackTrace() ; }
        finally
        {
            try { clientSocket.close () ; }
            catch ( IOException e ) { /* close failed */ }
        } 
    }
}
