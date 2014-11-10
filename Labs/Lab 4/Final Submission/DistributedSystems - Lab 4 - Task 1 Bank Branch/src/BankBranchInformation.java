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
    double bankBranchInitialBalance;
    double bankBranchWorkingBalance;
    int bankBranchNumber;
    int bankBranchPortNumber;
    
    /**
     *  Constructor for the BankBranchInformation class.
     *  This constructor is used to initialize global object variables. 
     * @param bankBranchName     - Name of the branch
     * @param bankBranchNumber   - The branches ID numer
     * @param bankBranchLocation - The location of the branch
     * @param bankBranchBalance  - The initial balnce the branch has at start.
     */
    public BankBranchInformation( String bankBranchName, int bankBranchNumber, String bankBranchLocation, double bankBranchInitialBalance, int portNumber) 
    {
        // Store values in the global variable
        this.bankBranchName            = bankBranchName;
        this.bankBranchNumber          = bankBranchNumber;
        this.bankBranchLocation        = bankBranchLocation;
        this.bankBranchInitialBalance  = bankBranchInitialBalance;
        this.bankBranchPortNumber      = portNumber;
        
        // On Object initialization set the initial balance to the working balance
        this.bankBranchWorkingBalance  = bankBranchInitialBalance;
    }
    
    /**
     * This functions is used to provide description of the bank branch
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
     * This functions is used to add to the working balance.
     * @param addToBalance - amount of money to add to the branches working balance
     */
    public void addToWorkingBalance(double addToBalance ) 
    {
        bankBranchWorkingBalance += addToBalance ;
    }
    
    /**
     * This function is used to subtract from the working balance.
     * @param subFromBalance - amount of money to subtract from the branches working balance
     */
    public void subtractFromWorkingBalance ( double subFromBalance ) 
    {
        bankBranchWorkingBalance -= subFromBalance ;
    }
    
    /**
     * Return the branch name
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
    public double getBranchInitialBalance() {
        return bankBranchInitialBalance;
    }
    
    /**
     * 
     * @return
     */
    public double getBranchWorkingBalance() {
        return bankBranchWorkingBalance;
    }
    
    /**
     * 
     * @return
     */
    public int getBranchPortNumber() {
        return bankBranchPortNumber;
    }
}
