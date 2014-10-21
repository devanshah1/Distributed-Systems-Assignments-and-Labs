import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public interface HelloServerInterface extends Remote
{

    /**
     * 
     * @return
     * @throws RemoteException
     */
    public String sayHello () throws RemoteException;

    /**
     * 
     * @param callbackClientObject
     * @throws RemoteException
     */
    public void registerForCallback ( HelloClientInterface callbackClientObject ) throws RemoteException ;

    /**
     * 
     * @param callbackClientObject
     * @throws RemoteException
     */
    public void unregisterForCallback ( HelloClientInterface callbackClientObject ) throws RemoteException ;
}
