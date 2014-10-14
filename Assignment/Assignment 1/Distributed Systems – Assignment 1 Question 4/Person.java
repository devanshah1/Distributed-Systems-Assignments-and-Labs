import java.io.Serializable;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class Person implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String             name;
    public String             place;
    public int                year;

    public Person ( String aName, String aPlace, int aYear )
    {
        name = aName;
        place = aPlace;
        year = aYear;
    }
    // followed by methods for accessing the
    // instance variables
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () 
    {
        return "   Name: " + this.name + "\n   Place: " + this.place + "\n   Year: " + this.year ;
    }
}
