import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 * The ElectionInterface contains methods that can be invoked
 * to register a vote or to retrieve the results of a election.
 * These interface extends Remote so that the functions can be 
 * accessed remotely.
 * @author Devan Shah 100428864
 *
 */
public interface ElectionInterface extends Remote 
{

    /**
     * The vote method contains 2 parameters that are used by the client to 
     * supply the candidate name and the voter's number. The vote method is 
     * used by the client to cast a vote.
     * @param candidateName - The name of the candiadate's name as a string.
     * @param voterNumber   - The voter's number as an integer. ( in a specific range )
     * @return boolean return true when vote is casted successful and false otherwise
     * @throws RemoteException
     */
    public boolean vote ( String candidateName, int voterNumber ) throws RemoteException ;
    
    /**
     * The result method is used to provide the client with the results of 
     * all the candidates and the amount of votes accumulated for them.
     * @return an vector of objects which contain the results.
     * @throws RemoteException
     */
    public Vector<Object> result () throws RemoteException ;
}