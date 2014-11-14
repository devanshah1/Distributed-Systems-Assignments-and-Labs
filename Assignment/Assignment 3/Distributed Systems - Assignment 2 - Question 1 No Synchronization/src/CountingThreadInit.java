import java.util.ArrayList;
import java.util.List;

/**
 * This class is uses to initialize counting threads to perform a count.
 * @author Devan Shah 100428864
 *
 */
public class CountingThreadInit
{
    /**
     * This is the main function that is used to construct the threads
     * to perform the counting. This will make use of the  counter object 
     * and the counting runnable thread.
     * @param args
     */
    public static void main ( String [] args )
    {
        // Initialize a new counter object
        Counter counterObject = new Counter();
        
        // Initialize the counting thread with the counter object
        CountingThread countingRunner = new CountingThread(counterObject);
        
        // Construct the array of threads that will be executed 
        List <Thread> threads = new ArrayList <Thread> () ;
        
        // Create and place the threads for the counting runner, also give them a name (just for the sake of it)
        threads.add ( new Thread ( countingRunner, "CounterOne" ) ) ;
        threads.add ( new Thread ( countingRunner, "CounterTwo" ) ) ;
        threads.add ( new Thread ( countingRunner, "CounterThree" ) ) ;
        
        // Start counting how long it will take to run the producing and consuming
        long start = System.currentTimeMillis () ;
        
        // Start the counting threads
        for ( Thread counterThread : threads )
        {
            counterThread.start () ;
        }
        
        // wait for the threads to complete and then join them 
        for ( Thread counterThread : threads )
        {
            try
            {
                counterThread.join () ;
            }
            // Catch the exception and provide the necessary information to the user.
            catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
        }
        
        // Find the duration of the producer and consumer  
        long duration = System.currentTimeMillis () - start ;
        System.out.println ( "Total Time Program Running: " + duration + " ms.\n" ) ;

        // Print the total valute of the counter after all the threads are joined. (down execution)
        System.out.println( "Counter Value after Execution ----->  " + counterObject.getCount() ) ;
    }
}
