import java.util.Date;



public class BankBranch implements Runnable
{

    BankBranchInformation bankBranchInformation;
    BankingImplementation branchCommunicator;
    
    public BankBranch (BankBranchInformation branchInformation, BankingImplementation branchCommunicator  )
    {
        this.bankBranchInformation = branchInformation;
        this.branchCommunicator = branchCommunicator;
    }

    @Override
    public void run ()
    {
        int timeInterval = Util.randomTimeInterval ();
        double transferAmount = Util.randomAmount (0.0, 1000.0);
        
        while (true) 
        {
            long sendingTime = new Date().getTime ();
            
            if ( sendingTime > ( sendingTime + timeInterval ) )
            {
                bankBranchInformation.subtractFromWorkingBalance ( transferAmount );
                branchCommunicator.send ( transferAmount, bankBranchInformation.bankBranchName );
                timeInterval = Util.randomTimeInterval ();
                transferAmount = Util.randomAmount (0.0, 1000.0);
            }
            else {
                bankBranchInformation.addToWorkingBalance( branchCommunicator.receive (bankBranchInformation.bankBranchName ));
            }
            
            System.out.println(bankBranchInformation.toString ());
            
            break;
        }
    }
}
