import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server
{
    public static void main ( String args [] )
    {
        InputStreamReader is =
                new InputStreamReader ( System.in );
        BufferedReader br = new BufferedReader ( is );
        String portNum, registryURL;
        try
        {
            System.out.println (
                    "Enter the RMIregistry port number:" );
            portNum = ( br.readLine () ).trim ();
            int RMIPortNum = Integer.parseInt ( portNum );
            startRegistry ( RMIPortNum );
            ServerImplication exportedObj =
                    new ServerImplication ();
            registryURL =
                    "rmi://localhost:" + portNum + "/callback";
            Naming.rebind ( registryURL, exportedObj );
            System.out.println ( "Callback Server ready." );
        }
        catch ( Exception re )
        {
            System.out.println (
                    "Exception in HelloServer.main: " + re );
        }
    }

    private static void startRegistry ( int RMIPortNum )
            throws RemoteException
    {
        try
        {
            Registry registry =
                    LocateRegistry.getRegistry ( RMIPortNum );
            registry.list ();
        }
        catch ( RemoteException e )
        {
            Registry registry =
                    LocateRegistry.createRegistry ( RMIPortNum );
        }
    }

}
