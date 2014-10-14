import java.io.*;

public class Order implements Serializable {
	public String name;
	public Address billing;
	public Address shipping;
	public Order(String n, Address b, Address s) {
		name = n;
		billing = b;
		shipping = s;
	}
}