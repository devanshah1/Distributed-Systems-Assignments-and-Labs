import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * This class is used to initialize the rmi election server. Also supports restoring 
 * from previously saved results. The file from which restore is attempted from is
 * "ElectionResultsRawData.ser", which contains serialized HashMap of the casted votes.
 * @author Devan Shah 100428864
 *
 */
public class ElectionServer
{

    /**
     * Main server function used to handle the election voting that comes from clients
     * and provide results when client requests. 
     * @param args - args[0] - The hostname to use, default is localhost if not provided
     */
    public static void main ( String args [] )
    {
        // Variable deceleration
        String hostname = "localhost" ; // Default host to use

        // Override the default values for hostname if passed through command line.
        if ( args [0].length () != 0 ) { hostname = args [0] ; }

        try
        {
            // Set the system property for "java.rmi.server.hostname".
            System.setProperty ( "java.rmi.server.hostname", hostname ) ;
            
            // Initialize the election interface to access all the remote functions.
            ElectionInterface electionAcceptor = new ElectionImplementation() ;
            
            // Declare registry variable
            Registry registry ;
            
            // This try catch is to make sure that the registry is created
            try 
            {
                // Try to get the remote object Registry for the local host on the default registry port of 1099.
                registry = LocateRegistry.getRegistry() ;
                registry.list() ; // Fetch the names bounded to the registry
            }
            // Catch the exception where communication with the registry fails and create the registry.
            catch ( RemoteException e ) 
            {
                // Create the registry on the default rmi port 1099
                System.out.println ( "RMI registry cannot be located at port " + Registry.REGISTRY_PORT ) ;
                registry = LocateRegistry.createRegistry ( Registry.REGISTRY_PORT ) ;
                System.out.println ( "RMI registry created at port " + Registry.REGISTRY_PORT ) ;
            }
            
            // Once the registry is successfully created, rebind the ElectionInterface to the remote reference created above.
            registry.rebind ( "ElectionInterface", electionAcceptor ) ;
            
            // Create the File handle for the file that is checked if an restore is needed
            File electionResultsRawData = new File ( "ElectionResultsRawData.ser" ) ;
            
            // Perform the restore only if the file exists.
            if ( electionResultsRawData.exists() ) 
            { 
                // Open up buffer reader to retrieve user response for a restore.
                BufferedReader userInput = new BufferedReader ( new InputStreamReader ( System.in ) ) ;
                String userResponse ;
                
                // Prompt the use for a response ( yes or no )
                System.out.println ( "Restore File \"ElectionResultsRawData.ser\" was found. Would you like to restore Election results form this file? ( yes or no )" ) ;
                userResponse = userInput.readLine () ;
                
                // Only perform a restore when the user has inputed a "yes"
                if ( userResponse.equalsIgnoreCase ( "yes" ) )
                {
                    System.out.println ( "Restoring the Election Server back to it's previous state before crash ... Please Wait." ) ;
                    electionAcceptor.restore () ;
                }
                else {
                    System.out.println ( "Not Restoring the Election Server ... Starting Election Server as normal." ) ;
                }
            }
            
            System.out.println ( "ElectionServer is READY to accept election votting ..... " ) ;
        }
        // Catch any exceptions and notify the user with detailed information.
        catch ( Exception e ) { System.err.println ( "ElectionServer Exceptions: " + e.getMessage () ) ; e.printStackTrace () ; }
    }
}
