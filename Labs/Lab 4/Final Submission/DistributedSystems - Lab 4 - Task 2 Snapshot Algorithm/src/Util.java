import java.util.Random;

/**
 * This is a utility class. For some general functions.
 * @author Devan Shah 100428864
 *
 */
public class Util
{
    /**
     * This function is used to provide a random double with in the
     * range user provides.
     * @param minAmount - the min amount for the random double
     * @param maxAmount - the max amount for the random double
     * @return randomTransferAmount - the random amount to transfer between branches
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
     * This function is used to generate a random time interval.
     * @return randomTimeInterval - random int used as the time interval
     */
    public static int randomTimeInterval () 
    {
        // Random number generator object creation
        Random randomGenerator = new Random();
        
        // Get a random time interval between 0 and 30 seconds
        int randomTimeInterval = randomGenerator.nextInt ( 30 );
        
        return randomTimeInterval;
    }
}
