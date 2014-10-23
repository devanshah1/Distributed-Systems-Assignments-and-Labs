public class Line
{
    String content;
    long   lineNumber;

    public Line ( String content, long lineNumber )
    {
    }

    boolean isEnd ()
    {
        return lineNumber < 0;
    }
}
