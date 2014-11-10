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
        long sendingTime = System.currentTimeMillis();
        
        while (true) 
        {
            long currentTime = System.currentTimeMillis();
            long waitingTime = sendingTime + (timeInterval*1000);
            
            if ( currentTime >= waitingTime && !branchCommunicator.sendReceiveFlag )
            {
                bankBranchInformation.subtractFromWorkingBalance ( transferAmount );
                branchCommunicator.send ( transferAmount, bankBranchInformation.bankBranchName );
                timeInterval = Util.randomTimeInterval ();
                transferAmount = Util.randomAmount (0.0, 1000.0);
                sendingTime = new Date().getTime ();
            }
            else if (branchCommunicator.sendReceiveFlag && !branchCommunicator.branchNameSender.equals ( bankBranchInformation.bankBranchName ))
            {
                bankBranchInformation.addToWorkingBalance( branchCommunicator.receive (bankBranchInformation.bankBranchName ));
                System.out.println(bankBranchInformation.toString ());
            }
        }
    }
}