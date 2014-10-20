import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class ServerImplication extends UnicastRemoteObject implements
        ServerInterface
{

    private Vector clientList;

    public ServerImplication () throws RemoteException
    {
        super ();
        clientList = new Vector ();
    }

    public String sayHello ()
            throws java.rmi.RemoteException
    {
        return ( "hello" );
    }

    public synchronized void registerForCallback (
            ClientInterface callbackClientObject )
            throws java.rmi.RemoteException
    {

        if ( ! ( clientList.contains ( callbackClientObject ) ) )
        {
            clientList.addElement ( callbackClientObject );
            System.out.println ( "Registered new client " );
            doCallbacks ();
        }
    }

    public synchronized void unregisterForCallback (
            ClientInterface callbackClientObject )
            throws java.rmi.RemoteException
    {
        if ( clientList.removeElement ( callbackClientObject ) )
        {
            System.out.println ( "Unregistered client " );
        }
        else
        {
            System.out.println (
                    "unregister: clientwasn't registered." );
        }
    }

    private synchronized void doCallbacks () throws java.rmi.RemoteException
    {

        System.out.println (
                "**************************************\n"
                        + "Callbacks initiated ---" );
        for ( int i = 0; i < clientList.size (); i++ )
        {
            System.out.println ( "doing " + i + "-th callback\n" );

            ClientInterface nextClient =
                    ( ClientInterface ) clientList.elementAt ( i );

            nextClient.notifyMe ( "Number of registered clients="
                    + clientList.size () );
        }
        System.out.println ( "********************************\n" +
                "Server completed callbacks ---" );
    }

}
