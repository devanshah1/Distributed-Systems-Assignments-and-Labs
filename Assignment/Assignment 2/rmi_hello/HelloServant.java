package rmi_hello;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServant extends UnicastRemoteObject implements Hello {
	public HelloServant() throws RemoteException {}
	
	public String sayHello() {
		System.out.println("running sayHello()");
		return "Hello world!";
	}
	
	public static void main(String args[]) {
		// args[0]: hostname of this remote object
		String host = args[0];
		try {
			System.setProperty("java.rmi.server.hostname", host);
			Hello aHello = new HelloServant();
			// Bind the remote object's stub in the registry
			Registry registry;
			try {
				registry = LocateRegistry.getRegistry();
				registry.list();
			}catch (RemoteException e){
				System.out.println("RMI registry cannot be located at port "
					 + Registry.REGISTRY_PORT);
				registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
				System.out.println("RMI registry created at port "
						+ Registry.REGISTRY_PORT);
			}
			
			registry.rebind("Hello", aHello);
			System.out.println("HelloServant ready");
		}catch (Exception e) {
			System.err.println("HelloServant exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
