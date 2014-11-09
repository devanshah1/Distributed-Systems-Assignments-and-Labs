
/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class BankingImplementation
{
    boolean sendReceiveFlag = false ;
    double amount;
    
    /**
     * 
     * @param amount
     * @param branchNameSender
     */
    public synchronized void send ( double amount, String branchNameSender )
    {
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
