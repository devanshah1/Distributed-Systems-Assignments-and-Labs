import java.util.ArrayList;
import java.util.List;

public class Program
{
    public static void main ( String [] args )
    {
        String inputFileName = args [0] ;
        int repeat = Integer.parseInt ( args [1] ) ;
        String outputFileName = args [2] ;
        // setup the data flow
        MessageQueue <Line> q1 = null ;
        FileIterator lines = new FileIterator ( inputFileName, repeat ) ;
        LineProducer p1 = new LineProducer ( lines, q1 ) ;
        LineConsumer c1 = new LineConsumer ( q1, outputFileName ) ;
        // place the producer and consumer in thread containers

        List <Thread> threads = new ArrayList <Thread> () ;
        threads.add ( new Thread ( p1 ) ) ;
        threads.add ( new Thread ( c1 ) ) ;
        
        long start = System.currentTimeMillis () ;
        
        // start the threads
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
            catch ( InterruptedException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace () ;
            }
        }
        long duration = System.currentTimeMillis () - start ;
        System.out.println ( "Total Duration: " + duration + " ms." ) ;
    }
}
