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
     * @param branchNameSender
     * @throws RemoteException
     */
    public void send ( double amount, String branchNameSender, int portNumber  ) throws RemoteException ;
    
    /**
     * 
     * @param branchNameReceiver
     * @return
     * @throws RemoteException
     */
    public double receive ( String branchNameReceiver ) throws RemoteException ;
}
