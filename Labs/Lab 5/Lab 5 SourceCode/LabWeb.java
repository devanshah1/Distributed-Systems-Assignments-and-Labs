import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;

import java.io.BufferedReader;
import java.io.FileReader;

public class LabWeb
{
    public static boolean authenticated = false;

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
                    String returnMessage = "Number of splat parameters: "
                            + req.splat ().length + "<br>";
                    returnMessage += "      Say: " + req.splat () [0] + "<br>";
                    returnMessage += "      to:  " + req.splat () [1] + "<br>";
                    returnMessage += "Computer saying \"" + req.splat () [0]
                            + "\" to \"" + req.splat () [1] + "\".<br>";
                    return returnMessage;
                } );
        before ( "/protected/*", ( req, resp ) ->
        {
            if ( authenticated )
            {
                resp.redirect ( "/hello" );
            }
                else
                {
                    halt ( 401, "YOU DO NOT HAVE PERMISSION TO ACCESS THIS SITE!" );
                }
            } );
        get ( "/authenticate", ( req, resp ) ->
        {
            authenticated = true;
            return "Authentication Successfull!!!!!!";
        } );
        get ( "/unauthenticate", ( req, resp ) ->
        {
            authenticated = false;
            return "Un-authentication Successfull!!!!!!!";
        } );
        get ( "/Lab_5_Report.txt", ( req, resp ) ->
        {
            String fullFile = null;

            try
            {
                BufferedReader reader = new BufferedReader(new FileReader("Lab_5_Report.txt"));
                String line = null ;
                
                line = reader.readLine();
                fullFile = line + "\n" ;
                
                while ((line = reader.readLine()) != null) 
                {
                    fullFile += line + "\n" ;
                }
                
                reader.close ();
            }
            catch ( Exception e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return convertPlainTextToHtml(fullFile);
        } );

    }

    public static String convertPlainTextToHtml ( String regrText )
    {
        // Variable Deceleration
        StringBuilder htmlStringBuilder = new StringBuilder ();
        boolean previousWasASpace = false;
        
        /**
         *  Loop through every single character in the regrText and convert it
         *  into HTML supported syntax.
         */
        for ( char character : regrText.toCharArray () )
        {
            // Handle space characters
            if ( character == ' ' )
            {
                if ( previousWasASpace )
                {
                    htmlStringBuilder.append ( "&nbsp;" );
                    previousWasASpace = false;
                    continue;
                }
                previousWasASpace = true;
            }
            else
            {
                previousWasASpace = false;
            }
            
            // Identify the current character and perform a conversion to html character if needed.
            switch ( character )
            {
                case '<' :
                    htmlStringBuilder.append ( "&lt;" );
                    break;
                case '>' :
                    htmlStringBuilder.append ( "&gt;" );
                    break;
                case '&' :
                    htmlStringBuilder.append ( "&amp;" );
                    break;
                case '"' :
                    htmlStringBuilder.append ( "&quot;" );
                    break;
                case '\n' :
                    htmlStringBuilder.append ( "<br>" );
                    break;
                case '\t' :
                    htmlStringBuilder.append ( "&nbsp; &nbsp; &nbsp;" );
                    break;
                default :
                    if ( character < 128 )
                    {
                        htmlStringBuilder.append ( character );
                    }
                    else
                    {
                        htmlStringBuilder.append ( "&#" ).append ( ( int ) character )
                                .append ( ";" );
                    }
            }
        }
        
        return htmlStringBuilder.toString ();
    }
}