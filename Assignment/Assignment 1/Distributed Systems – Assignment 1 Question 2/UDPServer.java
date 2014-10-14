import java.net.*;
import java.io.*;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class UDPServer
{
    public static void main ( String args [] )
    {
        DatagramSocket aSocket = null ;
        int expectedValue      = 0 ;
        int receivedDatagrams  = 0 ;
        int droppedDatagrams   = 0 ;
        
        // Starting Benchmark
        long startTime = System.nanoTime () ;
        
        try
        {
            aSocket = new DatagramSocket ( 6789 ) ;
            byte [] buffer = new byte [1000] ;
            
            while ( true )
            {
                DatagramPacket request = new DatagramPacket ( buffer, buffer.length ) ;
                
                aSocket.receive ( request ) ;
                int receivedValue = bytesToInt( request.getData() ) ;
                System.out.println ( "Received: " + receivedValue + ". Expected: " + expectedValue ) ;

                if ( receivedValue == expectedValue ) 
                {
                    receivedDatagrams++ ;
                }
                else if ( ( receivedValue < expectedValue ) && receivedValue > 0 )
                {
                    droppedDatagrams-- ;
                    receivedDatagrams++ ;
                }
                
                expectedValue = receivedValue + 1 ;
                droppedDatagrams = expectedValue - receivedDatagrams ;
                
                if ( ( ( receivedValue + droppedDatagrams ) == 99999 ) ) { break ; } ;
            }
        }
        catch ( SocketException e ) { System.out.println ( "Socket: " + e.getMessage () ) ; e.printStackTrace () ; }
        catch ( IOException e ) { System.out.println ( "IO: " + e.getMessage () ) ; e.printStackTrace () ; }
        finally
        {
            if ( aSocket != null )
                aSocket.close () ;
        }
        
        long endTime = System.nanoTime () ;
        
        // Total time of execution was
        long totalDuration = endTime - startTime ;
        totalDuration = totalDuration / 1000000000 ; 
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
