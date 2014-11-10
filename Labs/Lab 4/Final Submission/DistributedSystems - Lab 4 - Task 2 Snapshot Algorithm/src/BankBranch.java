
/**
 * This class is used to handle bank a branch thread.
 * This classifies which actions the bank branch needs to do.
 * @author Devan Shah 100428864
 *
 */
public class BankBranch implements Runnable
{
    // Declare the global objects
    BankBranchInformation bankBranchInformation;
    BankingImplementation branchCommunicator;
    
    /**
     * Constructor for the BankBranch class.
     * This constructor is used to initialize global object variables.
     * @param branchInformation
     * @param branchCommunicator
     */
    public BankBranch (BankBranchInformation branchInformation, BankingImplementation branchCommunicator  )
    {
        // Store the on creation objects into the global objects
        this.bankBranchInformation = branchInformation;
        this.branchCommunicator = branchCommunicator;
    }

    /**
     * This is the run function that is used to send money and receive money to
     * and from other threads. Sending occurs at random time intervals, and 
     * random amounts of money are sent to different branches.
     */
    @Override
    public void run ()
    {
        // Generate the initial random number for time interval and transfer amount.
        int timeInterval = Util.randomTimeInterval ();
        double transferAmount = Util.randomAmount (0.0, 1000.0);
        
        // Grab the current time in milliseconds, this is considered the sending time
        long sendingTime = System.currentTimeMillis();
        
        // Continuously run 
        while (true) 
        {
            // Grab the current time of iteration start
            long currentTime = System.currentTimeMillis();
            
            // Grab the amount of time that we need to wait to send money.
            long waitingTime = sendingTime + (timeInterval*1000);
            
            // When the current time has exceeded the waiting time start sending money to a random branch.
            if ( currentTime >= waitingTime && !branchCommunicator.sendReceiveFlag )
            {
                if ( branchCommunicator.recordedState == null && !branchCommunicator.recordedStates ) 
                {
                    branchCommunicator.recordedState = new SnapshotMarker(bankBranchInformation.bankBranchInitialBalance, 0.0);
                    branchCommunicator.recordedStates = true;
                    System.out.println ( bankBranchInformation.bankBranchName + " sending marker."  ) ;
                }
                else {
                    branchCommunicator.recordedState.setInTransit ( transferAmount );
                }
                bankBranchInformation.inTransit = 0.0;
                
                System.out.println(bankBranchInformation.toString ());
                
                // Before sending the money make sure to subtract from the current branch.
                bankBranchInformation.subtractFromWorkingBalance ( transferAmount );
                bankBranchInformation.inTransit = transferAmount;
                // Send the money through the branch communication channel for any branch to pick up. (considered random)
                branchCommunicator.send ( transferAmount, bankBranchInformation.bankBranchName );
                
                System.out.println(bankBranchInformation.toString ());
                
                // Grab New random numbers to send out, in the next send.
                timeInterval = Util.randomTimeInterval ();
                transferAmount = Util.randomAmount (0.0, 1000.0);
                
                // Grab the new time for sending
                sendingTime = System.currentTimeMillis();
            }
            // While this current branch is not sending or a send is not in process it will be receiving money from other branches
            else if (branchCommunicator.sendReceiveFlag && !branchCommunicator.branchNameSender.equals ( bankBranchInformation.bankBranchName ))
            {
                /**
                 * When there is money available to receive, call receive on the branch communication channel.
                 * Take the money that the receiver provides and add it the the branches working balance.
                 */
                bankBranchInformation.addToWorkingBalance( branchCommunicator.receive (bankBranchInformation.bankBranchName ));
                System.out.println(bankBranchInformation.toString ());
            }
        }
    }
}