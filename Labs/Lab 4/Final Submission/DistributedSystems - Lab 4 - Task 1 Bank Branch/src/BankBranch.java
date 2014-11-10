import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;



public class BankBranch implements Runnable
{

    BankBranchInformation bankBranchInformation;
    BankingImplementation branchCommunicator;
    String hostname = "localhost";
    int portnumber;
    BankingInterface bankingAcceptor;
    
    public BankBranch (BankBranchInformation branchInformation, BankingImplementation branchCommunicator  )
    {
        this.bankBranchInformation = branchInformation;
        this.branchCommunicator = branchCommunicator;
        this.portnumber = bankBranchInformation.bankBranchPortNumber;
    }

    @Override
    public void run ()
    {
        int timeInterval = Util.randomTimeInterval ();
        double transferAmount = Util.randomAmount (0.0, 1000.0);
        long sendingTime = System.currentTimeMillis();
        
        try
        {
            // Set the system property for "java.rmi.server.hostname".
            System.setProperty ( "java.rmi.server.hostname", hostname ) ;
            
            // Initialize the election interface to access all the remote functions.
            bankingAcceptor = new BankingImplementation() ;
            
            // Declare registry variable
            Registry registry ;
            
            // This try catch is to make sure that the registry is created
            try 
            {
                // Try to get the remote object Registry for the local host on the default registry port of 1099.
                registry = LocateRegistry.getRegistry() ;
                registry.list() ; // Fetch the names bounded to the registry
            }
            // Catch the exception where communication with the registry fails and create the registry.
            catch ( RemoteException e ) 
            {
                // Create the registry on the default rmi port 1099
                System.out.println ( "RMI registry cannot be located at port " + Registry.REGISTRY_PORT ) ;
                registry = LocateRegistry.createRegistry ( portnumber ) ;
                System.out.println ( "RMI registry created at port " + Registry.REGISTRY_PORT ) ;
            }
            
            // Once the registry is successfully created, rebind the ElectionInterface to the remote reference created above.
            registry.rebind ( "ElectionInterface", bankingAcceptor ) ;
        }
        // Catch any exceptions and notify the user with detailed information.
        catch ( Exception e ) { System.err.println ( "ElectionServer Exceptions: " + e.getMessage () ) ; e.printStackTrace () ; }
    
        while (true) 
        {
            long currentTime = System.currentTimeMillis();
            long waitingTime = sendingTime + (timeInterval*1000);
            
            if ( currentTime >= waitingTime && !branchCommunicator.sendReceiveFlag )
            {
//                bankBranchInformation.subtractFromWorkingBalance ( transferAmount );
//                branchCommunicator.send ( transferAmount, bankBranchInformation.bankBranchName );
//                timeInterval = Util.randomTimeInterval ();
//                transferAmount = Util.randomAmount (0.0, 1000.0);
//                sendingTime = new Date().getTime ();
                
                // Retrieve the registry that is defined on a specific hostname and port number. Should match the server.
                try
                {
                    Registry registry = LocateRegistry.getRegistry ( hostname, portnumber ) ;
                    
                    // Find and initialize the election interface for casting votes and retrieving results.
                    BankingInterface BankingClient = ( BankingInterface ) registry.lookup ( "BankingInterface" ) ;
                    BankingClient.send ( transferAmount, bankBranchInformation.bankBranchName, port );
                }
                // Catch the exception and provide the necessary information to the user.
                catch ( RemoteException e ) { System.out.println ( "Remote Exception: " + e.getMessage() ) ; e.printStackTrace () ; }
                catch ( Exception e ) { System.out.println ( "Registry: " + e.getMessage() ) ; e.printStackTrace () ; }
                
                timeInterval = Util.randomTimeInterval ();
                transferAmount = Util.randomAmount (0.0, 1000.0);
                sendingTime = new Date().getTime ();
            }
            else if (branchCommunicator.sendReceiveFlag && !branchCommunicator.branchNameSender.equals ( bankBranchInformation.bankBranchName ))
            {
//                bankBranchInformation.addToWorkingBalance( branchCommunicator.receive (bankBranchInformation.bankBranchName ));
//                System.out.println(bankBranchInformation.toString ());
                try
                {
                    bankBranchInformation.addToWorkingBalance( bankingAcceptor.receive ( bankBranchInformation.bankBranchName ));
                }
                catch ( RemoteException e ) { e.printStackTrace(); }
                System.out.println(bankBranchInformation.toString ());
            }
        }
    }
}
