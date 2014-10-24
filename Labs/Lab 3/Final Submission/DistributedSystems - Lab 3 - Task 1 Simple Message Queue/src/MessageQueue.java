import java.util.concurrent.BlockingQueue;

@SuppressWarnings ( "hiding" )
interface MessageQueue <Line> extends BlockingQueue <Line>
{
    Line take () ;
    void put ( Line m ) ;
}
