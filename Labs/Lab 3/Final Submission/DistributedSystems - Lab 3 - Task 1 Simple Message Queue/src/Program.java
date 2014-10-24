import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class Program
{
    /**
     * 
     * @param args
     */
    public static void main ( String [] args )
    {
        // Variable deceleration
        String inputFileName  = "inputFile.txt" ;
        int repeat            = 3 ;
        String outputFileName = "outputFile.txt" ;
        BlockingQueue <Line> blockingMessagingQueue = null ;
        
        // Override the default values for inputFileName, repeat and outputFileName if provided through command line.
        if ( args[0].length () != 0 ) { inputFileName  = args [0] ; }
        if ( args[1].length () != 0 ) { repeat         = Integer.parseInt ( args [1] ) ; }
        if ( args[2].length () != 0 ) { outputFileName = args [2] ; }
        
        // Setup the data flow
        MessageQueue messagingQueue = new MessageQueue ( blockingMessagingQueue ) ;
        
        FileIterator lines = new FileIterator ( inputFileName, repeat ) ;
        LineProducer p1 = new LineProducer ( lines, messagingQueue ) ;
        LineConsumer c1 = new LineConsumer ( messagingQueue, outputFileName ) ;
        
        // place the producer and consumer in thread containers
        List <Thread> threads = new ArrayList <Thread> () ;
        threads.add ( new Thread ( p1 ) ) ;
        threads.add ( new Thread ( c1 ) ) ;
        
        long start = System.currentTimeMillis () ;
        
        // Start the threads
        for ( Thread t : threads )
        {
            t.start () ;
        }
        // wait for the threads to complete
        for ( Thread t : threads )
        {
            try
            {
                t.join () ;
            }
            // Catch the exception and provide the necessary information to the user.
            catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
        }
        
        long duration = System.currentTimeMillis () - start ;
        System.out.println ( "Total Duration: " + duration + " ms." ) ;
    }
}
