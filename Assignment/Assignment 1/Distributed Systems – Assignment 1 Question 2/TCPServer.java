import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class TCPServer
{
    public static int numberOfBytesPerMessage = 1024 * 25 ;         // Default number of bytes per message.
    
    public static void main ( String args [] )
    {
        numberOfBytesPerMessage = 1024 * Integer.parseInt( args [0] ) ;         // Default number of bytes per message.
        
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
        catch ( IOException e ) { System.out.println ( "Connection: " + e.getMessage () ) ; }
    }

    public void run ()
    {
        int expectedValue      = 0 ;
        int receivedDatagrams  = 0 ;
        int droppedDatagrams   = 0 ;
        
        // Start benchmark
        long startTime = System.nanoTime () ;
        
        try
        {			                 // an echo server
            
            while ( true )
            {
                // Construct the byte array that will be used to store the values after they are read.
                // Used the size that is passe in
                byte [] dataArray = new byte [TCPServer.numberOfBytesPerMessage] ;
                in.readFully ( dataArray ) ;
                
                // Convert byte array into integer.
                int receivedValue = bytesToInt ( dataArray ) ;
                
                System.out.println ( "Received: " + receivedValue + ". Expected: " + expectedValue ) ;
                
                // received values verification process and check to see if there were dropped packets.
                if ( receivedValue == expectedValue ) 
                {
                    receivedDatagrams++ ;
                    expectedValue = receivedValue + 1 ;
                    droppedDatagrams = expectedValue - receivedDatagrams ;
                }
                else if ( receivedValue < expectedValue )
                {
                   droppedDatagrams-- ;
                   receivedDatagrams++ ;
                }
            }
        }
        catch ( EOFException e ) { System.out.println ( "EOF:" + e.getMessage () ) ; }
        catch ( IOException e ) { System.out.println ( "IO:" + e.getMessage () ); }
        finally
        {
            try { clientSocket.close () ; }
            catch ( IOException e ) { /* close failed */ }
        }
        
        long endTime = System.nanoTime () ;
        
        // Total time of execution was
        long totalDuration = endTime - startTime ;
        totalDuration = totalDuration / 1000000000; 
        
        System.out.println ( "\n\nTotal Duration for all the messages to be received was: " + totalDuration + " seconds\n\n") ;
        
        System.out.println( "Expected Datagrams: " + expectedValue ) ;
        System.out.println( "Received Datagrams: " + receivedDatagrams ) ;
        System.out.println( "Dropped Datagrams: " + droppedDatagrams ) ;
        System.out.println( "Received: " + ( (double) receivedDatagrams / expectedValue ) * 100 ) ;
        System.out.println( "Dropped: " + ( (double) droppedDatagrams / expectedValue ) * 100 ) ;
        System.out.println() ;
    }
    
    /**
     * Method to convert the bytes to integer
     * @param intBytes
     * @return integer value
     * @throws IOException
     */
    public static int bytesToInt ( byte [] intBytes ) throws IOException
    {
        int intValue = 0 ;
        
        // This is assuming a 4 bit conversion
        for ( int i = 0; i < 4; i++ ) 
        {
            int shift = (4 - 1 - i) * 8;
            intValue += ( intBytes[i] & 0x000000FF ) << shift;
        }
        
        return intValue ;
    }
}
