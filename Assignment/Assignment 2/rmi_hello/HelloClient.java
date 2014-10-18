package rmi_hello;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloClient {
	private HelloClient() {};
	
	public static void main(String args[]) {
		// args[0]: hostname of server where registry resides
		// args[1]: port of registry
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			Registry registry = LocateRegistry.getRegistry(host, port);
			Hello stub = (Hello) registry.lookup("Hello");
			String response = stub.sayHello();
			System.out.println("response: " + response);
		}catch (Exception e) {
			System.err.println("HelloClient exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
