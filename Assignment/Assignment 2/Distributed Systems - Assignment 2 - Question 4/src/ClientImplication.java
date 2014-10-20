import java.rmi.*;
import java.rmi.server.*;

public class ClientImplication extends UnicastRemoteObject
        implements ClientInterface
{

    public ClientImplication () throws RemoteException
    {
        super ();
    }

    public String notifyMe ( String message )
    {
        String returnMessage = "Call back received: " + message;
        System.out.println ( returnMessage );
        return returnMessage;
    }

}
