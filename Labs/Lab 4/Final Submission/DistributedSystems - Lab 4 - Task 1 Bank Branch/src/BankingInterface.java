import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Devan shah 100428864
 *
 */
public interface BankingInterface extends Remote 
{
    /**
     * 
     * @param amount
     * @param branchNameReceiver
     * @param branchNameSender
     * @throws RemoteException
     */
    public void send ( float amount, String branchNameReceiver, String branchNameSender ) throws RemoteException ;
    
    /**
     * 
     * @param amount
     * @param branchNameReceiver
     * @param branchNameSender
     * @throws RemoteException
     */
    public void receive ( float amount, String branchNameReceiver, String branchNameSender ) throws RemoteException ;
}
