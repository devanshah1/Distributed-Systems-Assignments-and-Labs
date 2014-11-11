/**
 * This is the counter class that is used to keep track of the counter and
 * return the counter when needed.
 * @author Devan Shah 100428864
 *
 */
public class Counter
{

    // Variable declaration
    int counter;
    
    /**
     * Constructor for the Counter class. 
     * This constructor is used to initialize the counter to 0.
     */
    public Counter()
    {
        // Initialize the counter to 0
        this.counter = 0;
    }
    
    /**
     * This function is used to increment the counter. 
     */
    public synchronized void increaseCount() 
    {
        // increment the counter by 1
        counter++;
        
        /**
         * Thread sleep is used at this point to simulate slow
         * data processing and modifications of the counter 
         * variable.
         */
        try
        {
            // Sleep for 55 milliseconds, this was based on speed of my computer.
            Thread.sleep(55);
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
    }
    
    /**
     * Returns the counter value at point of calling
     * @return counter - the counter value
     */
    public int getCount() 
    {
        // Return the counter
        return counter;
    }
}
