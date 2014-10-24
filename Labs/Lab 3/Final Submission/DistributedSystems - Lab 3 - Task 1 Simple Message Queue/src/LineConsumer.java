import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class LineConsumer implements Runnable
{
    String outputFileName ;
    MessageQueue messagesQueue ;
    
    /**
     * 
     * @param messagingQueue
     * @param outputFileName
     */
    public LineConsumer ( MessageQueue messagingQueue, String outputFileName )
    {
        this.outputFileName = outputFileName ;
        this.messagesQueue  = messagingQueue ;
    }

    /**
     * starts the consumption of strings from q1
     */
    public void run ()
    {
        try
        {
            PrintWriter fileWriter = new PrintWriter ( outputFileName, "UTF-8") ;
            
            for ( int i = 0; i < messagesQueue.size(); i++ ) 
            {
                fileWriter.println ( messagesQueue.takeMessage ().content.toString () ) ;
            }
            
            fileWriter.close () ;
        }
        // Catch the exception and provide the necessary information to the user.
        catch ( FileNotFoundException e ) { System.out.println ( "File Not Found Exception: " + e.getMessage () ) ; e.printStackTrace() ; }
        catch ( UnsupportedEncodingException e ) { System.out.println ( "Unsupported Encoding Exception: " + e.getMessage () ) ; e.printStackTrace() ; }
    }
}
