import java.io.*;

public class Address implements Serializable {
	public int streetNum;
	public String street;
	public Address(int n, String s) {
		streetNum = n;
		street = s;
	}
}
