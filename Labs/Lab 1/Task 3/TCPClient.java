import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCPClient class is used to establish a connection with the TCPServer over a dedicated port/IPAddress.
 * The TCPClient will send messages that user inputs from command line over to the server, and the server will print
 * the data that is received.  
 * @author Devan Shah 100428864
 *
 */
public class TCPClient
{
    /**
     * Main function for the TCPClient class, which handles performing all the connections and message sending.
     * @param args[0] - hostname/ipAddres for the communication.
     * @throws IOException 
     */
    public static void main ( String args [] ) throws IOException
    {
        // Variable declaration 
        Socket mySocket       = null ;        // Used to open up socket on hostname/port.
        PrintWriter output    = null ;        // Used for the PrintWriter to write message to server.
        String userInput      = " " ;         // User input will be stored here.
        String closeCondition = "quit" ;      // Terminate condition, used to terminate the client connection and server connection.
        String hostname       = "localhost" ; // Hostname or IpAddress of the server that needs to be connected to.
        int serverPort        = 7896 ;        // Hardcode port to make sure it matches the server everytime.
        BufferedReader input ;                // Used for reader to retrive information back from the server.
        String serverReply    = null ;        // Message sent back from the server.
        int timeout           = 300 ;        // Timeout value on the client socket.

        // Override default hostname or timeout in the case passed through command line.
        if ( args[0].length () != 0 ) { hostname = args [0] ; }
        if ( args[1].length () != 0 ) { timeout = Integer.parseInt( args [1] ) ; }
        
        try
        {
            // Run the while loop that will continuously look for user input and send it over to the server.
            // Terminate the wile loop only when "quit" is entered by the user or input is null.
            while ( userInput != null && !userInput.equalsIgnoreCase ( closeCondition ) )
            {
                // Construct the socket connection with hostname and port.
                mySocket = new Socket ( hostname, serverPort ) ;
                
                // Set timeout period (in milliseconds) so that a call to accept() for this socket will be
                // blocked for only this amount of time. If timeout expires a java.io.InterruptedIOException is 
                // raised.
                mySocket.setSoTimeout ( timeout ) ; // in milliseconds
                
                // Prompt the user for a string to pass over to the server to echo.
                System.out.print ( "Enter string to echo on server: " ) ;

                // Open buffer reader for the user to input string. Read string and pass it over to server.
                BufferedReader userInputs = new BufferedReader ( new InputStreamReader ( System.in ) ) ;
                userInput = userInputs.readLine () ;

                // Open up output stream on the same socket.
                // Open up the writer to send over the user input.
                OutputStream outStream = mySocket.getOutputStream () ;
                output = new PrintWriter ( new OutputStreamWriter ( outStream ) ) ;
                output.println ( userInput ) ; // Send user input to server.
                output.flush () ;              // flush the output to make sure there is no null results.
                
                // Initialization input streams.
                InputStream inStream = mySocket.getInputStream () ;
                input = new BufferedReader ( new InputStreamReader ( inStream ) ) ;
                
                // Retrieve the acknowledgement from the server stream.
                serverReply = input.readLine () ;
                
                if ( serverReply != null && !serverReply.equalsIgnoreCase ( closeCondition ) )
                {
                    // Print the acknowledgement message from the server.
                    System.out.println ( serverReply ) ;   
                }
                
                // Close the reader and writers.
                output.close () ;  // Close the writer connection.
                input.close () ;   // Close the reader connection.  
            }
        } // Handle catching the errors and printing the appropriate. 
        catch ( UnknownHostException e ) { System.out.println ( "Sock:" + e.getMessage () ) ; }
        catch ( EOFException e ) { System.out.println ( "EOF:" + e.getMessage () ) ; }
        catch ( InterruptedIOException e ){ System.out.println ( "Server has not Responding for: " + timeout + " milliseconds" ) ; }
        finally
        {
            // Close the socket only when "quit" is entered by the use and when socket connection is not null.
            if ( mySocket != null && userInput.equalsIgnoreCase ( closeCondition ) ) 
            {
                try { mySocket.close () ; }
                catch ( IOException e ) { System.out.println ( "Close:" + e.getMessage () ) ; }
            }
        }
    }
}
