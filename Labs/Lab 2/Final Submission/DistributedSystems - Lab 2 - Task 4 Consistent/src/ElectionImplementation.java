import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

/**
 * This class contains the method implementation that are defined in ElectionInterface.
 * @author Devan Shah 100428864
 *
 */
public class ElectionImplementation extends UnicastRemoteObject implements ElectionInterface
{
    // Default serialization ID
    private static final long serialVersionUID = 1L ;
    
    /*
     * Stores the votes that are casted will contain: 
     *     Integer - voters ID
     *     String - the candidate's name they voted for.
     */
    public Map <Integer, String> votesCasted ;
    
    /**
     * Constructor of the class that is used to initialize the votesCasted 
     * as a HashMap of integers and strings.    
     * @throws RemoteException
     */
    public ElectionImplementation () throws RemoteException
    {
        super () ;
        votesCasted = new HashMap <Integer, String>() ;
    }

    /**
     * This function is used to cast a vote, this is called remotely. 
     * The function also performs a verification task to make sure that
     * the same voter's number is not used to cast a vote.
     */
    @Override
    public synchronized boolean vote ( String candidateName, int voterNumber )
    {
        // Only add the vote into the main hashmap if the voter's number does not already exist.
        if ( votesCasted.containsKey ( voterNumber ) )
        {
            System.out.println ( "Sorry You can only Vote Once, you voter ID is: " + voterNumber + "\n" ) ;
            
            // Return false when the voter has already voted.
            return false ;
        }
        else {
            
            // Add the users vote into the main HashMao
            votesCasted.put ( new Integer ( voterNumber ), candidateName ) ;
            System.out.println ( "Vote for \"" + candidateName + "\"successfully casted by \"" + voterNumber ) ;
        }
        
        // variable deceleration 
        ObjectOutputStream electionRawDataOut ;
        
        /*
         * Open up a Object stream for a file where the current standing results of the election are stored.
         * Note: The raw data is stored into the file and the results are stored to the file in serialized 
         *       form to avoid tampering. 
         */
        try
        {
            // Construct the stream to write the HashMap of votes casted already.
            electionRawDataOut = new ObjectOutputStream ( new FileOutputStream ( "ElectionResultsRawData.ser" ) ) ;
            electionRawDataOut.writeObject ( votesCasted ) ; // Write HashMap to stream
            electionRawDataOut.flush() ; // flush the stream to make sure everything is written.
            electionRawDataOut.close() ; // Close the stream
        }
        catch ( IOException e ) { e.printStackTrace() ; }
        
        // return true on a successful vote cast   
        return true ;
    }
    
    /**
     * This function is used to return the casted results for all the candidates.
     * Following are the steps that the performed by this function:
     *     1. Iterate through the votesCasted hashmap
     *     2. Build the candidateResultsHolder hashmap with distinct candidates name
     *        and the gather now many votes where for that candidate.
     *     3. Iterate through the candidateResultsHolder hashmap that was build and 
     *        make store the results as a ElectionResults method in vector candidateResults.
     *     4. Return the candidateResults vector.
     */
    @Override
    public synchronized Vector<Object> result () throws RemoteException
    {
        // Declare vector and HashMap 
        Vector <Object> candidateResults             = new Vector<Object>() ;
        Map <String, Integer> candidateResultsHolder = new HashMap <String, Integer>() ;
        
        // Build the votesCasted Iterator to retrieve results from the HashMap
        Iterator <Entry <Integer, String>> castedVotesIterator = votesCasted.entrySet().iterator() ;
        
        // Loop through the iterator and build the candidateResultsHolder HashMap
        while ( castedVotesIterator.hasNext() )
        {
            // Construct the Entry to get the reference to the elements.
            Entry <Integer, String> castedVotesEntry = castedVotesIterator.next() ;
            
            /*
             *  Check the candidate value in HashMap castedVotesEntry and add them to candidateResultsHolder HashMap
             *  make sure that when adding the candidate values into candidateResultsHolder that they are distinct only.
             *  When a duplicate value is detected in castedVotesEntry HashMap increment the candidateResultsHolder 
             *  Hashmap value for the candidate key in question.
             */
            if ( candidateResultsHolder.containsKey ( castedVotesEntry.getValue() ) ) 
            {
                // Increment the value in candidateResultsHolder for the candidate in question by 1.
                candidateResultsHolder.put ( castedVotesEntry.getValue(), ( candidateResultsHolder.get ( castedVotesEntry.getValue() ) + 1 ) ) ;   
            }
            else {
                
                // A new candidate has been detected create a new entry in the candidateResultsHolder HashMap
                candidateResultsHolder.put ( castedVotesEntry.getValue(), new Integer (1) ) ;
            }
        }
        
        // Build the candidateResults Iterator to retrieve results from the HashMap
        Iterator <Entry <String, Integer>> candidateResultsIterator = candidateResultsHolder.entrySet().iterator() ;
        
        // Loop through the iterator and build the candidateResults vectors
        while ( candidateResultsIterator.hasNext() )
        {
            // Construct the Entry to get the reference to the elements.
            Entry <String, Integer> candidateResultEntry = candidateResultsIterator.next() ;
            
            // Construct an object of ElectionResults with the information from the candidateResultsHolder HashMap.
            ElectionResults resultsAdd = new ElectionResults ( candidateResultEntry.getKey(), candidateResultEntry.getValue() ) ;
            
            // Add the elements to the vector.
            candidateResults.addElement ( resultsAdd ) ;
        }
        
        System.out.println ( "The total number of candidates being returned to client: " + candidateResults.size () ) ;
        
        // return the candidateResults vector
        return candidateResults ;
    }

    /**
     * This function is used to restore the state of the votesCasted HashMap when 
     * the server crashes unexpectedly. 
     */
    @SuppressWarnings ( "unchecked" )
    @Override
    public void restore () throws RemoteException
    {
        // Variable deceleration 
        ObjectInputStream electionRawDataRestore;
        
        try
        {
            // Open the stream to retrieve the election raw data from the file
            electionRawDataRestore = new ObjectInputStream ( new FileInputStream ( "ElectionResultsRawData.ser" ) ) ;
            
            // Read the data in the file and store it in the votesCasted HashMap.
            votesCasted = ( Map <Integer, String> ) electionRawDataRestore.readObject() ;

            electionRawDataRestore.close() ; // Close the stream.
        }
        catch ( IOException e ) { e.printStackTrace() ; }
        catch ( ClassNotFoundException e ) { e.printStackTrace() ; }
        
    }
}
