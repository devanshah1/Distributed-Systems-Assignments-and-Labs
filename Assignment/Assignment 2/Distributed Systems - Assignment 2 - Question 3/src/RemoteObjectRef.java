import java.net.InetAddress;

class RemoteObjectRef
{
    private InetAddress ipAddress ;
    private int         port ;
    private int         time ;
    private int         objectNumber ;
    private Class       interf ;

    public InetAddress getIPaddress ()
    {
        return ipAddress;
    }

    public int getPort ()
    {
        return port;
    }
}
