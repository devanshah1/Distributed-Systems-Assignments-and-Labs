import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * The BankingInterface contains methods that can be invoked
 * to send money from one branch to another.
 * These interface extends Remote so that the functions can be 
 * accessed remotely.
 * @author Devan Shah 100428864
 */
public interface BankingInterface extends Remote 
{
    
    /**
     * This function is used to send the money to a branch from another branch.
     * This function makes use of threading calls wait and notify. Theses are used
     * so we make sure that we are only sending once and then a branch is receiving 
     * the same amount.
     * @param amount           - amount of money to transfer from one branch to another
     * @param branchNameSender - branch name of the sender
     * @throws RemoteException
     */
    public void send ( double amount, String branchNameSender) throws RemoteException ;
    
    /**
     * This function is used to acknowledge that the money is received by a thread.
     * @param branchNameReceiver - the name of the branch that is receiving the money.
     * @return amount - Return the amount of money that was received from another thread.
     * @throws RemoteException
     */
    public double receive ( String branchNameReceiver ) throws RemoteException ;
}
