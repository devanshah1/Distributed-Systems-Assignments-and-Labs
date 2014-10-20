package ass3q2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
    public String vote(String candidate, int voterNumber) throws RemoteException;

    public Result result(String candidate) throws RemoteException;
}
