import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloClientInterface extends Remote
{
    /**
     * 
     * @param message
     * @return
     * @throws RemoteException
     */
    public String notifyMe ( String message ) throws RemoteException;

}
