import java.io.Serializable;

/**
 * @author Devan Shah 100428864
 *
 */
public class BankBranchInformation implements Serializable
{

    /**
     * Default serialization constant for this object.
     */
    private static final long serialVersionUID = 1L ;
    
    // Variable Deceleration
    String bankBranchName;
    String bankBranchLocation;
    float bankBranchInitialBalance;
    float bankBranchWorkingBalance;
    int bankBranchNumber;
    
    /**
     *  
     * @param bankBranchName
     * @param bankBranchNumber
     * @param bankBranchLocation
     * @param bankBranchBalance
     */
    public BankBranchInformation( String bankBranchName, int bankBranchNumber, String bankBranchLocation, float bankBranchBalance) 
    {
        // Store values in the global variable
        this.bankBranchName            = bankBranchName;
        this.bankBranchNumber          = bankBranchNumber;
        this.bankBranchLocation        = bankBranchLocation;
        this.bankBranchInitialBalance  = bankBranchBalance;
        
        // On Object initialization set the initial balance to the working balance
        this.bankBranchWorkingBalance  = bankBranchBalance;
    }
    
    /**
     * 
     */
    @Override
    public String toString() 
    {
        String branchDescription = "Branch " + bankBranchName + ":\n" +
                                   "   Branch ID Number: " + bankBranchNumber + "\n" +
                                   "   Branch Location: " + bankBranchLocation + "\n" +
                                   "   Branch Initial Balance: " + bankBranchInitialBalance + "\n" +
                                   "   Branch Current Balance: " + bankBranchWorkingBalance; 
        return branchDescription;
        
    }
    
    /**
     * 
     * @param addToBalance
     */
    public void addToWorkingBalance(float addToBalance ) 
    {
        bankBranchWorkingBalance += addToBalance ;
    }
    
    /**
     * 
     * @param subFromBalance
     */
    public void subtractFromWorkingBalance ( float subFromBalance ) 
    {
        bankBranchWorkingBalance -= subFromBalance ;
    }
    
    /**
     * 
     * @return
     */
    public String getBranchName() {
        return bankBranchName;
    }
    
    /**
     * 
     * @return
     */
    public int getBranchNumber() {
        return bankBranchNumber;
    }
    
    /**
     * 
     * @return
     */
    public String getBranchLocation() {
        return bankBranchLocation;
    }
    
    /**
     * 
     * @return
     */
    public float getBranchInitialBalance() {
        return bankBranchInitialBalance;
    }
    
    /**
     * 
     * @return
     */
    public float getBranchWorkingBalance() {
        return bankBranchWorkingBalance;
    }
}
