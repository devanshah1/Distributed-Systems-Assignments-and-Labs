
public class SnapshotMarker
{

    double initialState ;
    double inTransit;
    
    public SnapshotMarker ( double initialState, double inTransit )
    {
        this.initialState = initialState;
        this.inTransit    = inTransit;
    }
    
    public void setInTransit (double amount) {
        this.inTransit = amount;
    }
}