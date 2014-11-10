import java.io.Serializable;

/**
 * This class is used to store the information of a bank branch.
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
    
    /**
     * Constructor for the BankBranchInformation class.
     * This constructor is used to initialize global object variables.
     * @param bankBranchName            - Name of the branch
     * @param bankBranchNumber          - The branch ID number
     * @param bankBranchLocation        - The location of the branch
     * @param bankBranchInitialBalance  - THe initial balance the branch has to start with.
     */
    public BankBranchInformation( String bankBranchName, int bankBranchNumber, String bankBranchLocation, double bankBranchInitialBalance) 
    {
        // Store values in the global variable
        this.bankBranchName            = bankBranchName;
        this.bankBranchNumber          = bankBranchNumber;
        this.bankBranchLocation        = bankBranchLocation;
        this.bankBranchInitialBalance  = bankBranchInitialBalance;
        
        // On Object initialization set the initial balance to the working balance
        this.bankBranchWorkingBalance  = bankBranchInitialBalance;
    }
    
    /**
     * This functions is used to provide description of the bank branch.
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
     * Return the bank branch name
     * @return bankBranchName - name of this  branch
     */
    public String getBranchName() {
        return bankBranchName;
    }
    
    /**
     * Return the branch number of a branch.
     * @return bankBranchNumber - branch number of this branch
     */
    public int getBranchNumber() {
        return bankBranchNumber;
    }
    
    /**
     * Return the location of a branch.
     * @return bankBranchLocation - location of this branch
     */
    public String getBranchLocation() {
        return bankBranchLocation;
    }
    
    /**
     * Return the initial balance of a branch.
     * @return bankBranchInitialBalance - initial balance of this branch
     */
    public double getBranchInitialBalance() {
        return bankBranchInitialBalance;
    }
    
    /**
     * Return the working balance of the branch.
     * @return bankBranchWorkingBalance - working balance of this branch
     */
    public double getBranchWorkingBalance() {
        return bankBranchWorkingBalance;
    }
}
