package Election_RMI;

 
	import java.rmi.*;
	public interface Election extends Remote{
	
		void vote(String name, int number) throws RemoteException;
		void result(String name, int votes) throws RemoteException;
	
	
	};
	
	//void vote(in string name, in long number);
	//void result(out string name, out long votes);
	
	