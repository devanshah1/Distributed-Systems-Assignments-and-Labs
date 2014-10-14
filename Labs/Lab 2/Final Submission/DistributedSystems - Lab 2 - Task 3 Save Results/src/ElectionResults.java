import java.io.Serializable;

/**
 * This ElectionResults class is used to store the results of the election.
 * This includes the name of the candidate and the number of votes that 
 * the candidate has accumulated. This will be a serialized object 
 * that can be accessed to retrieve the results of the election.
 * @author Devan Shah 100428864
 *
 */
class ElectionResults implements Serializable
{
    /**
     * Default serialization constant for this object.
     */
    private static final long serialVersionUID = 1L ;
    
    // Variable Deceleration 
    public String candidateName ;
    public int numberOfVotes ;
    
    /**
     * This method is used to set the candidateName and the number of votes that 
     * the candidate has accumulated.
     * @param candidate - The name of the candidate
     * @param numberOfVote - THe number of votes that the candidate has accumulated.
     */
    public ElectionResults ( String candidate, int numberOfVote ) 
    {
        this.candidateName = candidate ;
        this.numberOfVotes = numberOfVote ;
    }
    
    /**
     * Convert the method object into a readable string when called.
     * Since the object is serialized, this functions is required to make sure
     * that when trying to access the method objects the results are provided
     * correctly. Note: This overrides the normal toString function.
     */
    @Override
    public String toString ()
    {
        // Return back the name of the candidate and the number of votes that they have accumulated so far.
        return "   Candidate \"" + this.candidateName + "\" has accumulated: \"" + this.numberOfVotes + "\" votes." ;
    }
}