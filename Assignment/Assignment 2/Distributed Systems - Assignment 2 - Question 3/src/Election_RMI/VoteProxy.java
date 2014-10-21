package Election_RMI;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;

class VoteProxy
{
    RemoteObject          ref;
    private static Method voteMethod;
    private static Method resultMethod;
    static
    {
        try
        {
            voteMethod = Election.class.getMethod ( "vote", new Class []
            { java.lang.String.class, int.class } );
            resultMethod = Election.class.getMethod ( "result", new Class []
            { java.lang.String.class, int.class } );
        }
        catch ( Exception e )
        {
        }
    }

    public static void main ( String args [] ) throws RemoteException
    {
        try
        {
            RemoteObjectRef remoteObject = new RemoteObjectRef ();
            byte [] name = "Trevor".getBytes ( "UTF-8" );
            byte [] number = toByteArray ( 2013 );

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream ();
            outputStream.write ( name );
            outputStream.write ( number );

            byte [] electionVote = doOperation ( remoteObject, voteMethod,
                    outputStream.toByteArray () );

            name = "Franklin".getBytes ( "UTF-8" );
            number = toByteArray ( 7869 );

            byte [] results = doOperation ( remoteObject, resultMethod,
                    outputStream.toByteArray () );
            System.out.println ( "Result = "
                    + new String ( electionVote, "UTF-8" ) );
        }
        catch ( Exception e )
        {
        }
    }

    public static byte [] doOperation ( RemoteObjectRef o, Method m, byte [] arguments )
    {
        try
        {
            Object result = m.invoke ( o, new Object [] { new String ( arguments, "UTF-8" ), fromByteArray ( arguments ) } );

        }
        catch ( IllegalAccessException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        catch ( IllegalArgumentException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        catch ( InvocationTargetException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        catch ( UnsupportedEncodingException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace ();
        }
        return arguments;
    }

    static byte [] toByteArray ( int value )
    {
        return ByteBuffer.allocate ( 4 ).putInt ( value ).array ();
    }

    static int fromByteArray ( byte [] bytes )
    {
        return ByteBuffer.wrap ( bytes ).getInt ();
    }

}
