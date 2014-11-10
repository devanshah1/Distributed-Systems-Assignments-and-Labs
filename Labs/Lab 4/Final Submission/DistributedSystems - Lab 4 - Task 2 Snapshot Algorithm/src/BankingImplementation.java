
/**
 * This class is used to communicate with threads and send data between threads.
 * @author Devan Shah 100428864
 *
 */
public class BankingImplementation
{
    // Variable deceleration
    boolean sendReceiveFlag = false ;
    double amount;
    String branchNameSender;
    SnapshotMarker recordedState;
    boolean recordedStates = false;
    
    /**
     * This function is used to send the money to a branch from another branch.
     * This function makes use of threading calls wait and notify. Theses are used
     * so we make sure that we are only sending once and then a branch is receiving 
     * the same amount.
     * @param amount           - amount of money to transfer from one branch to another
     * @param branchNameSender - branch name of the sender
     */
    public synchronized void send ( double amount, String branchNameSender )
    {
        
        /**
         * Set the branch sender as global so that other class files can check this 
         * to determine who sent the money. This also makes sure that the same branch 
         * does not receive the money.
         * 
         */
        this.branchNameSender = branchNameSender;
        
        // Set the amount as global so that the receiver can grab this amount.
        this.amount = amount;
        
        // We wait around when money is already sent, until the receiver has received the money
        if (sendReceiveFlag) 
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        
        // Set the send receive flag to true so that the receiver knows to get the money.
        System.out.println("Branch " + branchNameSender + " is sending $" + amount);
        sendReceiveFlag = true;
        
        notify(); // Notify all the threads that money is ready to be received
    }
   
    /**
     * This function is used to acknowledge that the money is received by a thread.
     * @param branchNameReceiver - the name of the branch that is receiving the money.
     * @return amount - Return the amount of money that was received from another thread.
     */
    public synchronized double receive ( String branchNameReceiver )
    {
        // We wait around when there is no money sent.
        if ( !sendReceiveFlag ) 
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) { e.printStackTrace(); }
        }

        // Set the sendReceiveFlag back to false to acknowledge that money has been received by a thread.
        System.out.println("Branch " + branchNameReceiver + " has received $" + amount );
        sendReceiveFlag = false;
        
        // Notify all the threads that money is has been received.
        notify();
        
        return amount;
    }
}
