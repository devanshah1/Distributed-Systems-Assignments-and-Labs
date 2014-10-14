import java.net.*;
import java.io.*;

public class TCPServer
{
    public static int numberOfBytesPerMessage = 200 ;         // Default number of bytes per message.
    
    public static void main ( String args [] )
    {
        numberOfBytesPerMessage = Integer.parseInt( args [0] ) ;         // Default number of bytes per message.
        
        try
        {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket ( serverPort ) ;
            
            while ( true )
            {
                Socket clientSocket = listenSocket.accept ();
                Connection c = new Connection ( clientSocket ) ;
            }
        }
        catch ( IOException e ) { System.out.println ( "Listen :" + e.getMessage () ) ; }
    }
}

class Connection extends Thread
{
    DataInputStream  in;
    DataOutputStream out;
    Socket           clientSocket;
    
    public Connection ( Socket aClientSocket )
    {
        
        try
        {
            clientSocket = aClientSocket;
            in = new DataInputStream ( clientSocket.getInputStream () );
            out = new DataOutputStream ( clientSocket.getOutputStream () );
            this.start () ;
            
        }
        catch ( IOException e ) { System.out.println ( "Connection:" + e.getMessage () ) ; }
    }

    public void run ()
    {
        int expectedValue      = 0 ;
        int receivedDatagrams  = 0 ;
        int droppedDatagrams   = 0 ;
        
        try
        {			                 // an echo server
            while ( true )
            {
                byte [] dataArray = new byte [TCPServer.numberOfBytesPerMessage] ;
                in.readFully ( dataArray ) ;
                int receivedValue = bytesToInt ( dataArray ) ;
                System.out.println ( "Received: " + receivedValue + ". Expected: " + expectedValue ) ;
                
                if ( receivedValue == expectedValue ) 
                {
                    receivedDatagrams++ ;
                }
                
                expectedValue = receivedValue + 1 ;
                droppedDatagrams = expectedValue - receivedDatagrams ;
                
                System.out.println( "Expected Datagrams: " + expectedValue ) ;
                System.out.println( "Received Datagrams: " + receivedDatagrams ) ;
                System.out.println( "Dropped Datagrams: " + droppedDatagrams ) ;
                System.out.println( "Received: " + ( (double) receivedDatagrams / expectedValue ) * 100 ) ;
                System.out.println( "Dropped: " + ( (double) droppedDatagrams / expectedValue ) * 100 ) ;
                System.out.println() ;
            }
        }
        catch ( EOFException e ) { System.out.println ( "EOF:" + e.getMessage () ) ; }
        catch ( IOException e ) { System.out.println ( "IO:" + e.getMessage () ); }
        finally
        {
            try { clientSocket.close () ; }
            catch ( IOException e ) { /* close failed */ }
        } 
    }
    
    /**
     * 
     * @param intBytes
     * @return
     * @throws IOException
     */
    public static int bytesToInt ( byte [] intBytes ) throws IOException
    {
        int value = 0 ;
        
        for ( int i = 0; i < 4; i++ ) 
        {
            int shift = (4 - 1 - i) * 8;
            value += ( intBytes[i] & 0x000000FF ) << shift;
        }
        
        return value ;
    }
}
