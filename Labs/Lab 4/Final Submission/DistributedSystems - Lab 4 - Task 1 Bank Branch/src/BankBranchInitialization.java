import java.util.ArrayList;
import java.util.List;


public class BankBranchInitialization
{

    public static BankBranchInformation BranchOneInformation;
    public static BankBranchInformation BranchTwoInformation;
    public static BankingImplementation BranchCommunicator;
    
    public static void main ( String [] args )
    {
        // Construct the array of threads that will be executed 
        List <Thread> threads = new ArrayList <Thread> () ;
        
        BranchOneInformation = new BankBranchInformation ( "BranchOne", 1234, "Jane & Western", 2000.45, 1099 );
        BranchTwoInformation = new BankBranchInformation ( "BranchTwo", 5678, "King Street & Simcoe Street North", 2345.34, 1100 );
        BranchCommunicator = new BankingImplementation() ;
        
        BankBranch BranchOne = new BankBranch(BranchOneInformation, BranchCommunicator);
        BankBranch BranchTwo = new BankBranch(BranchTwoInformation, BranchCommunicator);
        
        // Place the branches in a thread containers
        threads.add ( new Thread ( BranchOne, BranchOne.bankBranchInformation.bankBranchName  ) ) ;
        threads.add ( new Thread ( BranchTwo, BranchTwo.bankBranchInformation.bankBranchName ) ) ;
        
        // Start counting how long it will take to run the producing and consuming
        long start = System.currentTimeMillis () ;
        
        // Start the threads
        for ( Thread branchThread : threads )
        {
            branchThread.start () ;
        }
        
        // wait for the threads to complete and then join the 
        for ( Thread branchThread : threads )
        {
            try
            {
                branchThread.join () ;
            }
            // Catch the exception and provide the necessary information to the user.
            catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
        }
        
        // Find the duration of the producer and consumer  
        long duration = System.currentTimeMillis () - start ;
        System.out.println ( "Total Time Program Running: " + duration + " ms." ) ;
    }

}
