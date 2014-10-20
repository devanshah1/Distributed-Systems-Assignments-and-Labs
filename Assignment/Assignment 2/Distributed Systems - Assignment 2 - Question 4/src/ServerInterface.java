import java.rmi.*;

public interface ServerInterface extends Remote
{

    public String sayHello ()
            throws java.rmi.RemoteException;

    public void registerForCallback (
            ClientInterface callbackClientObject
            ) throws java.rmi.RemoteException;

    public void unregisterForCallback (
            ClientInterface callbackClientObject )
            throws java.rmi.RemoteException;
}
