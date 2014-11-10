
/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class BankingImplementation implements BankingInterface
{
    boolean sendReceiveFlag = false ;
    double amount;
<<<<<<< HEAD
    String branchNameSender;
    int branchSenderPortNumber;
=======
>>>>>>> parent of 721161d... Lab 4 - Task 1 Sort of working with Threads
    
    /**
     * 
     * @param amount
     * @param branchNameSender
     */
    public synchronized void send ( double amount, String branchNameSender, int portNumber )
    {
<<<<<<< HEAD
        this.branchNameSender = branchNameSender;
        this.branchSenderPortNumber = portNumber;
        
=======
>>>>>>> parent of 721161d... Lab 4 - Task 1 Sort of working with Threads
        if (sendReceiveFlag) 
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) { e.printStackTrace(); }
        }
        this.amount = amount;
        System.out.println("Branch " + branchNameSender + " is sending $" + amount);
        sendReceiveFlag = true;
        notify();
    }
    
    /**
     * 
     * @param amount
     * @param branchNameReceiver
     * @return 
     */
    public synchronized double receive ( String branchNameReceiver )
    {
        if ( !sendReceiveFlag ) 
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) { e.printStackTrace(); }
        }

        System.out.println("Branch " + branchNameReceiver + " has received $" + amount );
        sendReceiveFlag = false;
        notify();
        
        return amount;
    }
}
