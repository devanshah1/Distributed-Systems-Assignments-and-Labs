import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;

public class ShapeListClient{
	   public static void main(String args[]){
			String host = args[0];
			int port = Integer.parseInt(args[1]);
			String option = "read";
			String type = "rectangle";
			if(args.length > 2) option = args[2]; // read or write
			if(args.length> 3) type = args[3]; // circle, line, etc.
			System.out.println("option = " + option + ", shape = " + type);

			try {
				Registry registry = LocateRegistry.getRegistry(host, port);
				ShapeList aList = (ShapeList) registry.lookup("ShapeList");
				System.out.println("Found server");
				
				Vector<Shape> shapeVec = aList.allShapes();
				System.out.println("Got vector");
				if(option.equals("read")){
					for(int i = 0; i < shapeVec.size(); i++){
						GraphicalObject g = ((Shape)shapeVec.elementAt(i)).getAllState();
						g.print();
					}
				} else {
					GraphicalObject g = new GraphicalObject(type, 
						new Rectangle(50,50,300,400), Color.red, Color.blue, false);
					System.out.println("Created graphical object");
					aList.newShape(g);
					System.out.println("Stored shape with ShapeList object on server");
				}
			}catch(RemoteException e) {System.out.println("allShapes: " + e.getMessage());
			}catch(Exception e) {System.out.println("Registry: " + e.getMessage());
		}
	}
}
