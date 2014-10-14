import java.net.*;
import java.io.*;

/**
 * TCPServer class is used open a socket for listening and accepting communications from the TCPClient.
 * The communication will always be over a dedicated port/IPAddress, the TCPClient will send messages 
 * over to the server and the server will print the data that is received.
 * @author Devan Shah 100428864
 *
 */
public class TCPServer
{
    /**
     * Main function for the TCPServer class, which handles performing all the connections and message receiving.
     * @param args - no argunemnt required at this time.
     */
    public static void main ( String args [] )
    {
        // Variable declaration
        int serverPort = 7896 ;
        
        try
        {
            // Open up listening socket for communication with the client.
            ServerSocket listenSocket = new ServerSocket ( serverPort ) ;
            
            // Forever loop that will perform the action of listening for communication from client.
            while ( true )
            {
                // Variable declaration
                Socket clientSocket = null ; // Used to open up socket for client communication.
                
                // Wait for communication from the client, once received accept communication stream and perform the 
                // necessary actions.
                clientSocket = listenSocket.accept () ;
                Connection clientConnection = new Connection ( clientSocket ) ; // Used to open client connection for communication
                clientConnection.run () ; // Open up communication stream on socket.
                
                // Close up the listening socket once user has inputed "quit".
                if ( clientConnection.closeingCondition ) { listenSocket.close () ; }
            }
        } // Handle catching the errors and printing the appropriate. 
        catch ( IOException e ) { System.out.println ( "Listen : " + e.getMessage () ) ; }
    }
}

/**
 * Connection class is used to open the input socket for client to send information to and also start up the thread.
 * @author Devan Shah 100428864
 *
 */
class Connection extends Thread
{
    // Variable declaration
    BufferedReader input ;
    Socket         clientSocket ;
    public boolean closeingCondition = false ;

    /**
     * Start up the requested communication stream.
     * @param aClientSocket
     */
    public Connection ( Socket aClientSocket )
    {
        try
        {
            // Socket initialization 
            clientSocket = aClientSocket ;

            // Initialization input streams.
            InputStream inStream = clientSocket.getInputStream () ;
            input = new BufferedReader ( new InputStreamReader ( inStream ) ) ;

            // Start up the thread.
            this.start () ;
            
        } // Handle catching the errors and printing the appropriate. 
        catch ( IOException e ) { System.out.println ( "Connection: " + e.getMessage () ) ; }
    }

    /**
     * Used to print the input that is passed into the server from the client.
     */
    public void run () 
    {
        // Variable declaration
        String data           = null ;
        String closeCondition = "quit" ;
        PrintWriter output    = null ;
        
        try
        { 
            // Retrieve the user input from the stream.
            data = input.readLine () ;
            
            // Print the data as received only when the user input is not null and user input is not "quit".
            if ( data != null && !data.equalsIgnoreCase ( closeCondition ) )
            {
                System.out.println ( "Received: " + data ) ;
                
                // Open up output stream on the same socket.
                // Open up the writer to send back acknowledgement that message was received. 
                OutputStream outStream = clientSocket.getOutputStream () ;
                output = new PrintWriter ( new OutputStreamWriter ( outStream ) ) ;
                output.println ( "Server Received: " + data ) ; // Send user input to server.
                output.flush () ;                               // flush the output to make sure there is no null results.
                output.close () ;                               // Close the writer connection.
            }
            

        } // Handle catching the errors and printing the appropriate. 
        catch ( EOFException e ) { System.out.println ( "EOF: " + e.getMessage () ) ; }
        catch ( IOException e ) { /*System.out.println( "IO: " + e.getMessage() ) ;*/ }
        catch ( IllegalArgumentException e ) { throw null ; }
        finally
        {
            try
            {
                // Close the client connections when user input is "quit".
                if ( data != null && data.equalsIgnoreCase ( closeCondition ) )
                {
                    clientSocket.close () ;    // Close the client socket connection
                    closeingCondition = true ; // Set the closing condition for TCPServer::main to pick up
                }
            } // Handle catching the errors and printing the appropriate. 
            catch ( IOException e ) { System.out.println ( "IO: " + e.getMessage () ) ; }
        }
    }
}
