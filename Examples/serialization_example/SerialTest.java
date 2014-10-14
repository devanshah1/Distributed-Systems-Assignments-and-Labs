import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class SerialTest extends parent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int version = 66;
	contain con = new contain();
	public int getVersion() {
	return version;
	}
	public static void main(String args[]) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SerialTest st = new SerialTest();
		oos.writeObject(st);
		oos.flush();
		oos.close();
		
		FileInputeStream fis = new FileInputStream("temp.out");
		ObjectInputStream ois = new ObjectInputStream(fis);
		st = (SerialTest) ois.readObject();
		ois.close();
		System.out.println("version is: " + st.getVersion() + " and contain version is: " + st.con.containVersion);
	}
}
