import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
class MessageQueue
{
    public BlockingQueue<Line> messagingQueue ;
    
    /**
     * 
     * @param messagingQueue
     */
    public MessageQueue ( BlockingQueue<Line> messagingQueue ) 
    {
        this.messagingQueue = messagingQueue ;
    }
    
    /**
     * 
     * @param q
     */
    public void putMessage ( Line q ) 
    {
        try
        {
            messagingQueue.put ( q ) ;
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
    }
    
    /**
     * 
     */
    public Line takeMessage () 
    {
        Line lineExtract = null ;
        
        try
        {
            lineExtract = messagingQueue.take () ;
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
        
        return lineExtract ;
    }
    
    /**
     * 
     * @return
     */
    public int size () 
    {
        return messagingQueue.size () ;
    }
    
}
