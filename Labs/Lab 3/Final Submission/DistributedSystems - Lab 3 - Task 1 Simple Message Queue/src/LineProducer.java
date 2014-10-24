/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class LineProducer implements Runnable
{
    FileIterator linesInput ;
    MessageQueue messagesQueue ;
    
    /**
     * 
     * @param input
     * @param messagingQueue
     */
    public LineProducer ( FileIterator input, MessageQueue messagingQueue )
    {
        this.linesInput    = input ;
        this.messagesQueue = messagingQueue ;
    }

    /**
     * puts strings from q1
     */
    @Override
    public void run ()
    {
        for ( Line line : linesInput ) 
        {
            messagesQueue.putMessage ( line ) ;
        }
    }

}
