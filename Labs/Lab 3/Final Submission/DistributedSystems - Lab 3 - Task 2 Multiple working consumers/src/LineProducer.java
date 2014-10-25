import java.util.Iterator;

/**
 * This class is used to produce the lines and add them to the messaging queue
 * for the consumer to consume.
 * @author Devan Shah 100428864
 *
 */
public class LineProducer implements Runnable
{
    // Declare the File Iterator that is used as the producers
    FileIterator linesInput ;
    
    /**
     * This constructor of the class is used to store the FileIterator of lines
     * that extracted from the file and build using the Line object.
     * @param input
     * @param messagingQueue
     */
    public LineProducer ( FileIterator input )
    {
        // Store the input on object creation in the global linesInput 
        this.linesInput    = input ;
    }

    /**
     * This run functions overrides the normal run function to perform the 
     * action of iterating through the lines that are stored in the linesInput
     * and passing then in to the messagingQueue for consumption.
     */
    @Override
    public void run ()
    {
        // Retrieve the iterator object of the linesInput
        Iterator <Line> linesIterator = linesInput.iterator () ;
        
        // Iterator through the iterator until all entries have been accounted for
        while ( linesIterator.hasNext () ) 
        {   
            // Grab the next entry in the iterator
            Line lineChange = linesIterator.next() ;
            
            /*
             * Make sure that the users know was is going on by printing that the producer is 
             * doing something.
             */
            System.out.println ( "Line Producer is Producing: " + lineChange.content ) ;
            
            // Store the line into the messagingQueue
            Program.messagingQueue.putMessage ( lineChange ) ;
            
            // Once the iterator had finished with the entry remove it as it is no longer needed.
            linesIterator.remove () ;
        }
        
        // Add end of file message to the queue showing that we have finished producing the messages.
        Line endOfFileMessage = new Line ( "END-OF-FILE", -1 ) ;
        Program.messagingQueue.putMessage ( endOfFileMessage ) ;
    }

}
