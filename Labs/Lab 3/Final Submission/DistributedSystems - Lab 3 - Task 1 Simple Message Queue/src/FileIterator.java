import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is used to build an iterator which contains Line objects.
 * Each of the line objects contain the line string of the message and 
 * the line number of the message.
 * @author Devan Shah 100428864
 *
 */
public class FileIterator implements Iterable <Line>
{
    // Declare the Iterator object that will contain the Line objects
    Iterator <Line> linesIterator ;
    
    /**
     * Declare the Collection object that will contain the collection of the Line object.
     * The collection is in the form of an ArrayList.
     */
    Collection <Line> linesExtractor = new ArrayList <Line>() ;
    
    /**
     * Constructor of the class that is used to build the Collection object 
     * with Line objects that are build from the file. The Line objects contain 
     * string of the line and the line number of the line. This functions also
     * supports to read the file multiple times.
     * @param filename - Name of the file to read and build the objects for
     * @param repeat - The number of times to read the same file.
     */
    public FileIterator ( String filename, int repeat )
    {   
        
        // For loop used to read the file multiple times depending on the repeat value.
        for ( int i = 0; i < repeat; i++ )
        {
           // Variable declaration
           String currentLine ;
           int lineCount = 1 ;
           
           try
           {
              // Create the buffer reader for the file
              BufferedReader bufferReader = new BufferedReader ( new FileReader ( filename ) ) ;
            
              /**
               * Loop through the file and add the lines into Line object and then 
               * add the Line object to the Collection.
               */
              while ( ( currentLine = bufferReader.readLine () ) != null ) 
              {
                 // Construct Line object with currentline and line number to 
                 Line lineObject = new Line ( currentLine, lineCount ) ;
                 
                 // Add the Line object to the collection for iteration later on.
                 linesExtractor.add ( lineObject ) ;
                 lineCount++ ; // Increase the line count on every read.
              }
              
              // Close the buffer reader
              bufferReader.close () ;
           }
           // Catch any exceptions and notify the user with detailed information.
           catch ( FileNotFoundException e ) { System.out.println ( "File Not Found Exception: " + e.getMessage () ) ; e.printStackTrace() ; }
           catch ( IOException e ) { System.out.println ( "IO Exception: " + e.getMessage () ) ; e.printStackTrace() ; } 
        }
    }

    
    @Override
    /**
     * This function is used to construct the iterator of Lines object
     * with the use of the Collection object.
     */
    public Iterator <Line> iterator ()
    {
        // Create an iterator of the collection of Lines objects
        linesIterator = linesExtractor.iterator () ;
        
        return linesIterator ;
    }
}
