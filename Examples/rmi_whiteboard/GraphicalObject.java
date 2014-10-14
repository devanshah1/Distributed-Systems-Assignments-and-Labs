import java.awt.Rectangle;
import java.awt.Color;
import java.io.Serializable;

public class GraphicalObject implements Serializable {
	private static final long serialVersionUID = 1L;
	public String type;
	public Rectangle enclosing;
	public Color line;
	public Color fill;
	public boolean isFilled;

	public String candidateName ;
    public int votersId ;
    
    public GraphicalObject() {}
    
    /**
     * 
     * @param candidate
     * @param voterID
     */
    public ElectionResults ( String candidate, int voterID ) 
    {
        candidateName = candidate ;
        votersId      = voterID ;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () 
    {
        return "   Candidate Name: " + candidateName + " Voter's ID: " + votersId ;
    }
	
}
