import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This class is used to store the messaging queue for producing and consuming.
 * @author Devan Shah 100428864
 *
 */
class MessageQueue
{
    // Declare the global blocking queue for the class
    public BlockingQueue<Line> messagingQueue ;
    
    /**
     * This constructor is used to store the passed blocking queue into the 
     * global blocking queue.
     * @param messagingQueue - The initialization of the blocking queue
     */
    public MessageQueue ( BlockingQueue<Line> messagingQueue ) 
    {
        // Store the input on object creation in the global messagingQueue variable.
        this.messagingQueue = messagingQueue ;
    }
    
    /**
     * This function is used to put the line objects into the messaging queue (blocking queue)
     * @param lineObject - This is the line object that will go into the messaging queue.
     */
    public void putMessage ( Line lineObject ) 
    {
        try
        {
            // Place the line object into the queue to make it ready for consuming
            messagingQueue.put ( lineObject ) ;
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
    }
    
    /**
     * This function is used to take the line object out of the messaging queue
     * and return it to the caller.
     * @return lineExtract - the first element that is available in the messaging queue
     */
    public Line takeMessage () 
    {
        // Declare the line that is extracted as a Line object
        Line lineExtract = null ;
        
        try
        {
            // Take the first Line object out of the messaging queue
            lineExtract = messagingQueue.poll ( 2, TimeUnit.MILLISECONDS ) ;
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
        
        return lineExtract ;
    }
    
    /**
     * Get the size of the messaging queue to determine when done
     * @return
     */
    public int size ()
    {
        // return the size of the queue
        return messagingQueue.size () ;
    }
}