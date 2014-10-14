import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class UDPClient
{
    /**
     * 
     * @param args
     */
    public static void main ( String args [] )
    {
        // Variable deceleration
        DatagramSocket aSocket      = null ;
        String hostname             = "localhost" ; // Default hostname
        int messagesToSend          = 100 ;         // Default number of messages to send
        int numberOfBytesPerMessage = 1024*25 ;     // Default number of bytes per message.
        int serverPort              = 6789 ;        // Default port number
        
        // Override default hostname or timeout in the case passed through command line.
        if ( args[0].length () != 0 ) { hostname = args [0] ; }
        if ( args[1].length () != 0 ) { messagesToSend = Integer.parseInt( args [1] ) ; }
        if ( args[2].length () != 0 ) { numberOfBytesPerMessage = 1024 * Integer.parseInt( args [2] ) ; }
        
        try
        {
            aSocket             = new DatagramSocket () ;
            
            // Loop through the amount of messages user wants to send with fixed bytes and send them to the server.
            for ( int messageSend = 0; messageSend < messagesToSend; messageSend++ ) 
            {
                // Variable deceleration
                byte [] intBytes    = intToBytes( messageSend ) ;
                byte [] buffer      = new byte [ numberOfBytesPerMessage - intBytes.length ] ;
                
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream () ;
                outputStream.write( intBytes ) ;
                outputStream.write( buffer ) ;

                byte [] bytesToSend = outputStream.toByteArray () ;
                outputStream.flush () ;
                outputStream.close () ;
                
                System.out.println( "Sending " + messageSend + " as " + bytesToSend.length + " bytes" ) ;
                
                InetAddress hostIP  = InetAddress.getByName ( hostname ) ;
                DatagramPacket data = new DatagramPacket ( bytesToSend, hostname.length(), hostIP, serverPort ) ;
                
                aSocket.send ( data ) ;
            }
        }
        catch ( SocketException e ) { System.out.println ( "Socket: " + e.getMessage () ) ; e.printStackTrace () ; }
        catch ( IOException e ) { System.out.println ( "IO: " + e.getMessage () ) ; e.printStackTrace () ; }
        finally
        {
            if ( aSocket != null )
                aSocket.close () ;
        }
    }
    
    /**
     * Method is used to convert the integer value into a byte array to send over to the server.
     * @param x
     * @return byte array 
     * @throws IOException
     */
    public static byte [] intToBytes ( int value ) throws IOException
    {
        // Open up the streams
        ByteArrayOutputStream arrayStream = new ByteArrayOutputStream () ;
        DataOutputStream out = new DataOutputStream ( arrayStream ) ;
        
        // Write the integer value to the steam 
        out.writeInt ( value ) ;
        out.flush () ;
        out.close () ;
        
        // extract the byte array from the stream.
        byte [] intBytesArray = arrayStream.toByteArray () ; 
        arrayStream.flush () ;
        arrayStream.close () ;
        
        return intBytesArray ;
    }
}
