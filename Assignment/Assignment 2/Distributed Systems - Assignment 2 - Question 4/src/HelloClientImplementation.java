import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloClientImplementation extends UnicastRemoteObject implements HelloClientInterface
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @throws RemoteException
     */
    public HelloClientImplementation () throws RemoteException
    {
        super ();
    }

    /**
     * 
     */
    public String notifyMe ( String message )
    {
        String returnMessage = "Call back received: " + message;
        System.out.println ( returnMessage );
        return returnMessage;
    }

}
