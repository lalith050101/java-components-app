package firappRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class firapplication {

	public static void main(String[] args) throws Exception {
		RMIServer server = new RMIServer();
		LocateRegistry.createRegistry(2000);
		Naming.bind("rmi://localhost:2000/lalith", server);
		System.out.println("server started..........");
	}
}
