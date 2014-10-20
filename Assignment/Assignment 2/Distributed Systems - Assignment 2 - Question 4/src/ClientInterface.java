import java.rmi.*;

public interface ClientInterface extends java.rmi.Remote
{

    public String notifyMe ( String message ) throws java.rmi.RemoteException;

}
