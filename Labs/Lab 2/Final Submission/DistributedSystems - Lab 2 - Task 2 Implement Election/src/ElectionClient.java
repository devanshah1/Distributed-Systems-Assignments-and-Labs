import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

/**
 * This class is used to initialize the election client which is used to cast votes
 * or retrieve results for an election.
 * 
 * @author Devan Shah 100428864
 *
 */
public class ElectionClient 
{    
    /**
     * This is the main function of the election client that is responsible for 
     * initialize the rmi services to communicate with the server that is on the
     * same host and port. Handles performing a vote for a candidate and also 
     * retrieving the results of the election.
     * 
     * @param args
     */
    public static void main ( String args[] )
    {
        // Variable deceleration 
        String hostname      = "localhost" ;  // Default host to use 
        int portnumber       = 1099 ;         // Default rmi port
        String option        = "vote" ;       // By default the client will accept voting.
        String candidateName ;                // Stores the candidate's name
        int voterNumber ;                     // Stores the 
        
        // Override the default values for hostname, portnumber, candidateName and votenumber if provided through command line.
        if ( args[0].length () != 0 ) { option        = args [0] ; }
        if ( args[1].length () != 0 ) { hostname      = args [1] ; }
        if ( args[2].length () != 0 ) { portnumber    = Integer.parseInt ( args [2] ) ; }
        
        try
        {
            // Retrieve the registry that is defined on a specific hostname and port number. Should match the server.
            Registry registry = LocateRegistry.getRegistry ( hostname, portnumber ) ;
            
            // Find and initialize the election interface for casting votes and retrieving results.
            ElectionInterface electionVoter = ( ElectionInterface ) registry.lookup ( "ElectionInterface" ) ;
            System.out.println ( "Found Election server" ) ;
            
            // The client supports 2 options retrieving the results from the server for the current standing of the
            // election or casting a vote for a candidate. These options are provided through command line.
            if ( option.equals ( "results" ) ) 
            {
                /*
                 * Retrieve the election results using the remote function result.
                 * The result function returns a Vector of Objects, the objects contains
                 * objects of ElectionResults method for each of the candidates that 
                 * are in the current election.
                 */
                Vector<Object> electionVoterResults = electionVoter.result () ;
                
                System.out.println ( "Current Election Results are: " ) ;
                
                // Loop through the retrieved vector of objects and convert them to string and print to the client.
                for ( int i = 0; i < electionVoterResults.size(); i++ )
                {
                    ElectionResults resultEntry = ( ( ElectionResults ) electionVoterResults.elementAt (i) ) ;
                    
                    // Convert the ElectionResults to a string that is readable for the user.
                    String result = resultEntry.toString() ;
                    System.out.println ( result ) ;
                }
            }
            else {
                
                // Open up buffer reader to retrieve the information about user and candidate they like to vote for.
                BufferedReader userInput = new BufferedReader ( new InputStreamReader ( System.in ) ) ;
                
                // Ask for candidate name
                System.out.println ( "Enter Candidate Name: " ) ;
                candidateName = userInput.readLine () ;
                
                // Ask for the users voter ID
                System.out.println ( "Enter your voter ID: " ) ;
                voterNumber = Integer.parseInt ( userInput.readLine () ) ;
                
                // Send the candidate name and the voter Number to the remote vote function for validation and approval.
                boolean voteCastSuccess = electionVoter.vote ( candidateName, voterNumber ) ;
                
                // Make sure that the vote that was casted by the use was successful 
                if ( voteCastSuccess ) { System.out.println ( "Congratulations you have casted a vote for: " + candidateName ) ; }
                else { System.out.println ( "Sorry a vote has already been casted with voter ID: " + voterNumber ) ; }
                
                // Close the buffer reader.
                userInput.close() ;
            }
            
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( RemoteException e ) { System.out.println ( "Remote Exception: " + e.getMessage() ) ; e.printStackTrace () ; }
        catch ( Exception e ) { System.out.println ( "Registry: " + e.getMessage() ) ; e.printStackTrace () ; }
    }
}