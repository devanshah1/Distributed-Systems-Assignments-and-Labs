import java.io.*;
import java.net.*;

public class OrderClient {
	public static void main(String args[]) {
		Order ord = new Order("John",
				new Address(1111, "King St."),
				new Address(-1, "N/A"));

		System.out.println("Initially: " + ord.name + "," + ord.billing.streetNum + "," + ord.billing.street
				+ "," + ord.shipping.streetNum + "," + ord.shipping.street);

		Socket aSock = null;
		try {
			aSock = new Socket("pl1.bit.uoit.ca", 9876);
			ObjectOutputStream out = new ObjectOutputStream(aSock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(aSock.getInputStream());
			out.writeObject(ord);
			Order x = (Order) in.readObject();
			System.out.println("Received: " + x.name + "," + x.billing.streetNum + "," + x.billing.street
					+ "," + x.shipping.streetNum + "," + x.shipping.street);

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SocketException e) { System.out.println("SocketException: " + e.getMessage());
		}catch(IOException e) { System.out.println("IOException: " + e.getMessage());
		}finally { if (aSock != null) try{aSock.close();}catch(IOException e) {} }
	}
}
