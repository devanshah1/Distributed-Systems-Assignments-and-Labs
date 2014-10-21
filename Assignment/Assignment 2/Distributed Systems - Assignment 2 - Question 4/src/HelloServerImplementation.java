import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class HelloServerImplementation extends UnicastRemoteObject implements
        HelloServerInterface
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Vector <HelloClientInterface> clientList;

    public HelloServerImplementation () throws RemoteException
    {
        super ();
        clientList = new Vector <HelloClientInterface> ();
    }

    public String sayHello () throws RemoteException
    {
        return ( "hello" );
    }

    public synchronized void registerForCallback ( HelloClientInterface callbackClientObject )
    {
        try
        {
           if ( ! ( clientList.contains ( callbackClientObject ) ) )
           {
              clientList.addElement ( callbackClientObject );
              System.out.println ( "Registered new client " );
              doCallbacks ();
           }
        }
        catch ( RemoteException e ) { e.printStackTrace(); }
    }

    public synchronized void unregisterForCallback ( HelloClientInterface callbackClientObject )
    {
        if ( clientList.removeElement ( callbackClientObject ) )
        {
            System.out.println ( "Unregistered client " );
        }
        else
        {
            System.out.println ( "unregister: client wasn't registered." );
        }
    }

    private synchronized void doCallbacks () throws RemoteException
    {

        System.out.println ( "Callbacks initiated" );
        
        for ( int i = 0; i < clientList.size (); i++ )
        {
            System.out.println ( "Callbacking" + i + "\n" );

            HelloClientInterface nextClient = ( HelloClientInterface ) clientList.elementAt ( i );

            nextClient.notifyMe ( "Number of registered clients=" + clientList.size () );
        }
        System.out.println ( "Server completed callbacks" );
    }

}
