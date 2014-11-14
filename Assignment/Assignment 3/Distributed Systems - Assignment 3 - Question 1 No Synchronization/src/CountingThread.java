/**
 * This class is used to run the counting thread and perform the additions on the counter.
 * This class makes use of the counter object that is passed in.
 * @author Devan Shah 100428864
 *
 */
public class CountingThread implements Runnable
{
    // Counter Object Deceleration
    Counter counter;
    
    /**
     * This is the constructor of the class that is used to define the counter object.
     * @param counterObject - store the counter object
     */
    public CountingThread ( Counter counterObject )
    {
        // Store the values in the global counter object
        this.counter = counterObject;
    }

    /**
     * This is the main run function of the Runnable thread that is used to increment the counter.
     */
    @Override
    public void run ()
    {
        // Increase the counter 10 times to get a total of 10 count increases per thread execution.
        for ( int i = 0; i < 10; i++ )
        {
            // Increment the counter
            counter.increaseCount();
        }
    }
}
