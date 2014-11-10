import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class that is responsible for constructing the branches and 
 * creating and starting the threads for the bank branches.
 * @author Devan Shah 100428864
 *
 */
public class BankBranchInitialization
{

    // Stores the important information for the branch
    public static BankBranchInformation BranchOneInformation;
    public static BankBranchInformation BranchTwoInformation;
    
    // Stores the communication medium for the branches (send and receive money)
    public static BankingImplementation BranchCommunicator;
    
    /**
     * This is the main function that will be constructing the bank branch information, creating the thread
     * objects for the branches and starting the threads for the bank branches.
     * @param args
     */
    public static void main ( String [] args )
    {
        // Construct the array of threads that will be executed 
        List <Thread> threads = new ArrayList <Thread> () ;
        
        // Create bank branch information objects
        BranchOneInformation = new BankBranchInformation ( "BranchOne", 1234, "Jane & Western", 2000.45);
        BranchTwoInformation = new BankBranchInformation ( "BranchTwo", 5678, "King Street & Simcoe Street North", 2345.34);
        
        // Create the banking communication object
        BranchCommunicator = new BankingImplementation() ;
        
        // Create the main bank branch objects, with each of the branch information objects and the same communication object.
        BankBranch BranchOne = new BankBranch ( BranchOneInformation, BranchCommunicator );
        BankBranch BranchTwo = new BankBranch ( BranchTwoInformation, BranchCommunicator );
        
        // Place the bank branches in a thread containers, with a thread name matching the branch name
        threads.add ( new Thread ( BranchOne, BranchOne.bankBranchInformation.bankBranchName  ) ) ;
        threads.add ( new Thread ( BranchTwo, BranchTwo.bankBranchInformation.bankBranchName ) ) ;
        
        // Start counting how long it will take to run the producing and consuming
        long start = System.currentTimeMillis () ;
        
        // Start the bank branch threads
        for ( Thread branchThread : threads )
        {
            branchThread.start () ;
        }
        
        // wait for the threads to complete and then join them 
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
