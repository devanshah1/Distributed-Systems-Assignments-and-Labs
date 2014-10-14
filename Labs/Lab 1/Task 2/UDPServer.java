import java.net.*;
import java.io.*;

public class UDPServer
{
    public static void main ( String args [] )
    {
        DatagramSocket aSocket = null ;
        int expectedValue      = 0 ;
        int receivedDatagrams  = 0 ;
        int droppedDatagrams   = 0 ;
        
        try
        {
            aSocket = new DatagramSocket ( 6789 ) ;
            byte [] buffer = new byte [100] ;
            
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
//                else if ( receivedValue < expectedValue )
//                {
//                    droppedDatagrams-- ;
//                    receivedDatagrams++ ;
//                }
                
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
        catch ( SocketException e ) { System.out.println ( "Socket: " + e.getMessage () ) ; e.printStackTrace () ; }
        catch ( IOException e ) { System.out.println ( "IO: " + e.getMessage () ) ; e.printStackTrace () ; }
        finally
        {
            if ( aSocket != null )
                aSocket.close ();
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
