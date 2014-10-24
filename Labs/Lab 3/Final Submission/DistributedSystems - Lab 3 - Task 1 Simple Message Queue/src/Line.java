/**
 * This class is used to construct the Line object for every line that is 
 * read from a file.
 * @author Devan Shah 100428864
 *
 */
public class Line
{
    // Variable deceleration 
    String content ;
    long   lineNumber ;

    /**
     * Constructor of the Line class is used to set the line string and 
     * line number when Line object is created.
     * @param content - The contents of the line that is read from the file
     * @param lineNumber - The line number of the content
     */
    public Line ( String content, long lineNumber )
    {
        // Store values in the global variable
        this.content    = content ;
        this.lineNumber = lineNumber ;
    }

    /**
     * This function is used to determine if the end of the file is reached
     * @return 1 when lineNumber is less then 0 else return 0
     */
    boolean isEnd ()
    {
        return lineNumber < 0 ;
    }
}
