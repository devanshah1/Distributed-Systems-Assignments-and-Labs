import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class ShapeListServant extends UnicastRemoteObject implements ShapeList {
	private static final long serialVersionUID = 1L;
	private Vector<Shape> theList;
	private int version;

	public ShapeListServant() throws RemoteException{
		theList = new Vector<Shape>();
		version = 0;
	}

	public Shape newShape(GraphicalObject g) throws RemoteException{
		version++;
		Shape s = new ShapeServant(g, version);
		theList.addElement(s);
		return s;
	}

	public Vector<Shape> allShapes() throws RemoteException{
		return theList;
	}

	public int getVersion() throws RemoteException{
		return version;
	}
}
