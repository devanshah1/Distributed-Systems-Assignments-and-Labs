import java.rmi.Remote;

public interface ClientInterface extends Remote
{

    public String notifyMe ( String message ) throws java.rmi.RemoteException;

}
