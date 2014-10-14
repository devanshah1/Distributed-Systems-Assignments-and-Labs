import java.net.*;
import java.io.*;

public class TCPClient
{
    
    public static void main ( String args [] )
    {
        // Variable declaration 
        Socket mySocket             = null ;        // Used to open up socket on hostname/port.
        String hostname             = "localhost" ; // Hostname or IpAddress of the server that needs to be connected to.
        int serverPort              = 7896 ;        // Hardcode port to make sure it matches the server everytime.
        int messagesToSend          = 100 ;         // Default number of messages to send
        int numberOfBytesPerMessage = 200 ;         // Default number of bytes per message.
        
        // Override default hostname or timeout in the case passed through command line.
        if ( args[0].length () != 0 ) { hostname = args [0] ; }
        if ( args[1].length () != 0 ) { messagesToSend = Integer.parseInt( args [1] ) ; }
        if ( args[2].length () != 0 ) { numberOfBytesPerMessage = Integer.parseInt( args [2] ) ; }
        
        try
        {
            // Construct the socket connection with hostname and port.
            mySocket = new Socket ( hostname, serverPort ) ;
            DataOutputStream output = new DataOutputStream ( mySocket.getOutputStream () ) ;
            
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
                
                output.write ( bytesToSend ) ;
                output.flush () ;
            }
        }
        catch ( UnknownHostException e ) { System.out.println ( "Sock: " + e.getMessage () ) ; }
        catch ( EOFException e ) { System.out.println ( "EOF: " + e.getMessage () ) ; }
        catch ( IOException e ) { System.out.println ( "IO: " + e.getMessage () ) ; }
        finally
        {
            if ( mySocket != null )
                try { mySocket.close () ; }
                catch ( IOException e ) { System.out.println ( "close:" + e.getMessage () ) ; }
        }
    }
    
    /**
     * 
     * @param x
     * @return
     * @throws IOException
     */
    public static byte [] intToBytes ( int value ) throws IOException
    {
        ByteArrayOutputStream arrayStream = new ByteArrayOutputStream () ;
        DataOutputStream out = new DataOutputStream ( arrayStream ) ;
        
        out.writeInt ( value ) ;
        out.flush () ;
        out.close () ;
        
        byte [] int_bytes = arrayStream.toByteArray () ; 
        arrayStream.flush () ;
        arrayStream.close () ;
        
        return int_bytes ;
    }
}
