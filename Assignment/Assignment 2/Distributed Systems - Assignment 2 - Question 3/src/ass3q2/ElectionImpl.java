package ass3q2;

import java.rmi.RemoteException;

public class ElectionImpl implements Election {

    @Override
    public String vote(final String candidate, final int voterNumber) throws RemoteException {
        return "Voter " + voterNumber + ", you have voted for " + candidate + ".";
    }

    @Override
    public Result result(final String candidate) throws RemoteException {
        final Result result = new Result(candidate, 2);
        return result;
    }
}
