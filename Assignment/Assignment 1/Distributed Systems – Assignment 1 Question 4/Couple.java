import java.io.Serializable;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
class Couple implements Serializable
{
    /**
         * 
         */
    private static final long serialVersionUID = 1L;
    public Person             one;
    public Person             two;

    public Couple ( Person a, Person b )
    {
        one = a;
        two = b;
    }
}
