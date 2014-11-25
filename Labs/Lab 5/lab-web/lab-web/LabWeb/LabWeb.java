import static spark.Spark.get;

public class LabWeb
{
    public static void main ( String [] args )
    {
        get ( "/hello", ( req, resp ) ->
        {
            return "Hello World.";
        } );
        get ( "/add",
                ( req, resp ) ->
                {
                    String message;

                    try
                    {
                        String a = req.queryParams ( "a" );
                        String b = req.queryParams ( "b" );
                        if ( a == null )
                        {
                            message = "The \"a\" parameter is missing.";
                        }
                        else if ( b == null )
                        {
                            message = "The \"b\" parameter is missing.";
                        }
                        else
                        {
                            float result = Float.parseFloat ( a )
                                    + Float.parseFloat ( b );
                            message = a + " + " + b + " = " + result;
                        }
                    }
                    catch ( Exception e )
                    {
                        message = e.toString ();
                    }
                    return message;
                } );
        get ( "/hello/:name", ( req, resp ) ->
        {
            return "Hello " + req.params ( ":name" );
        } );
        get ( "/say/*/to/*",
                ( req, resp ) ->
                {
                    String returnMessage = "Ndumber of splat parameters: "
                            + req.splat ().length + "\n";
                    returnMessage += "      Say: " + req.splat () [0] + "\n";
                    returnMessage += "      to:  " + req.splat () [1] + "\n";
                    returnMessage += "Computer saying \"" + req.splat () [0]
                            + "\" to \"" + req.splat () [1] + "\".\n";
                    return returnMessage;
                } );
    }
}
