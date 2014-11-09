import java.util.Random;

/**
 * 
 * @author Devan Shah 100428864
 *
 */
public class Util
{
    /**
     * 
     * @return
     */
    public static double randomAmount (double minAmount, double maxAmount ) 
    {       
        // Random number generator object creation
        Random randomGenerator = new Random();
        
        // random transfer amount between min and max amount.
        double randomTransferAmount = minAmount + (maxAmount - minAmount) * randomGenerator.nextDouble();
        
        return randomTransferAmount;
    }
    
    /**
     * 
     * @return
     */
    public static int randomTimeInterval () 
    {
        // Random number generator object creation
        Random randomGenerator = new Random();
        
        // Get a random time interval between 0 and 60 seconds
        int randomTimeInterval = randomGenerator.nextInt ( 30 );
        
        return randomTimeInterval;
    }
}
