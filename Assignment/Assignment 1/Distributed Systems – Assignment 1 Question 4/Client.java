import java.net.*;
import java.io.*;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class Client
{
    public static void main ( String args [] )
    {
        
        // Variable declaration 
        Socket mySocket             = null ;        // Used to open up socket on hostname/port.
        String hostname             = "localhost" ; // Hostname or IpAddress of the server that needs to be connected to.
        int serverPort              = 7896 ;        // Hardcode port to make sure it matches the server every time.
        
        // Override default hostname or timeout in the case passed through command line.
        if ( args[0].length () != 0 ) { hostname = args [0] ; }
        
        try
        {
            mySocket  = new Socket ( hostname, serverPort ) ;
            Couple c  = new Couple ( null, null ) ;
            Person p1 = new Person ( "Bob", "Markham", 2014 ) ;
            c.one = p1 ;

            ObjectOutputStream oos = new ObjectOutputStream ( mySocket.getOutputStream () ) ;
            oos.writeObject ( c ) ;
            System.out.println ( "(Couple) Sent: \n  Person one\n" + c.one.toString() ) ;

            ObjectInputStream ois = new ObjectInputStream ( mySocket.getInputStream () ) ;
            c = ( Couple ) ois.readObject () ;
            System.out.println ( "\n(Couple) Received:" ) ;
            System.out.println ( "\n  Person one\n" + c.one.toString() ) ;
            System.out.println ( "\n  Person Two\n" + c.two.toString() ) ;

        }
        catch ( UnknownHostException e ) { System.out.println ( "Sock: " + e.getMessage () ) ; }
        catch ( EOFException e ) { System.out.println ( "EOF: " + e.getMessage () ) ; }
        catch ( IOException e ) { System.out.println ( "IO: " + e.getMessage () ) ; }
        catch ( ClassNotFoundException e ) { e.printStackTrace() ; }
        finally
        {
            if ( mySocket != null )
                try { mySocket.close () ; }
                catch ( IOException e ) { System.out.println ( "close:" + e.getMessage () ) ; }
        }
    }
}
