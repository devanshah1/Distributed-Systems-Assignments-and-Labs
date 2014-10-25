import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This is the main program class for the producing of messages from a file and 
 * consuming the messages based on k edit distance from the query word. Start the 
 * producer and consumer threads.
 * @author Devan Shah 100428864
 *
 */
public class Program
{
    // Declare the global messaging queues, query word and the edit distance threshold 
    public static MessageQueue messagingQueue ;
    public static String queryWord ;
    public static int editDistanceThreshold ;
    
    /**
     * This is the main program that is used to perform all the action listed in the class definition above.
     * @param args - args[0] - name of the input file that is used to read the messages (default is inputFile.txt)
     *             - args[1] - the number of times to read the file (default is 1)
     *             - args[2] - name of the output file that is used to store the results of the consumer. 
     *                         (default is outputFile.txt)
     *             - args[3] - query word used by the consumer to to filter the lines by edit distance.
     *             - args[4] - edit distance threshold
     */
    public static void main ( String [] args )
    {
        // Variable deceleration
        String inputFileName  = "inputFile.txt" ;
        int repeat            = 1 ;
        String outputFileName = "outputFile.txt" ;
        queryWord             = "systems" ;
        editDistanceThreshold = 3 ;
        
        /**
         * Override the default values for inputFileName, repeat, outputFileName, 
         * queryWord and editDistanceThreshold if provided through command line.
         */
        if ( args[0].length () != 0 ) { inputFileName         = args [0] ; }
        if ( args[1].length () != 0 ) { repeat                = Integer.parseInt ( args [1] ) ; }
        if ( args[2].length () != 0 ) { outputFileName        = args [2] ; }
        if ( args[3].length () != 0 ) { queryWord             = args [3] ; }
        if ( args[4].length () != 0 ) { editDistanceThreshold = Integer.parseInt ( args [4] ) ; }
       
        /**
         *  Construct the files iterator which contains all the lines in the file.
         *  File reading repeats depending on the value of repeat.
         */
        FileIterator lines = new FileIterator ( inputFileName, repeat ) ;
        
        // Create the blocking queue based on the number of lines that are read from the file
        BlockingQueue <Line> blockingMessagingQueue = new ArrayBlockingQueue <Line> ( lines.linesExtractor.size () + 1 ) ;
        
        // Create the messaging queue with the empty blocking queue.
        messagingQueue = new MessageQueue ( blockingMessagingQueue ) ;
        
        // Create the producer
        LineProducer p1 = new LineProducer ( lines ) ;
        
        // Create the consumer
        LineConsumer c1 = new LineConsumer ( outputFileName ) ;
        
        // Place the producer and consumer in thread containers
        List <Thread> threads = new ArrayList <Thread> () ;
        threads.add ( new Thread ( p1 ) ) ;
        threads.add ( new Thread ( c1 ) ) ;
        
        // Start counting how long it will take to run the producing and consuming
        long start = System.currentTimeMillis () ;
        
        // Start the threads
        for ( Thread t : threads )
        {
            t.start () ;
        }
        
        // wait for the threads to complete and then join the 
        for ( Thread t : threads )
        {
            try
            {
                t.join () ;
            }
            // Catch the exception and provide the necessary information to the user.
            catch ( InterruptedException e ) { System.out.println ( "Interrupted Exception detected: " + e.getMessage () ) ; e.printStackTrace () ; }
        }
        
        // Find the duration of the producer and consumer  
        long duration = System.currentTimeMillis () - start ;
        System.out.println ( "Total Duration: " + duration + " ms." ) ;
    }
}
