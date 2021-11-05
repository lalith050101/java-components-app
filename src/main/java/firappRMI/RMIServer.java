package firappRMI;

import java.io.FileInputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Enumeration;
import java.util.Properties;

import implementation.ImplementationFramework;

public class RMIServer extends UnicastRemoteObject implements MyApp {

	protected RMIServer() throws RemoteException {
		super();
	}

	@Override
	public Object getObject() throws RemoteException {
		int count = 0;
		try {
			Properties props = new Properties();
			props.load(new FileInputStream("service-config.properties"));

			Enumeration enu = props.elements();

			int noOfServices = 0;
			while (enu.hasMoreElements()) {
				noOfServices++;
				enu.nextElement();
			}

			Class c[] = new Class[noOfServices];
			Object o[] = new Object[noOfServices];

			enu = props.elements();
			while (enu.hasMoreElements()) {
				String serviceConfigFileName = (String) enu.nextElement();
				props.load(new FileInputStream(serviceConfigFileName));
				String interfaceName = props.getProperty("interfacename");
				String interfaceImpl = props.getProperty("interfaceimpl");
				
				c[count] = Class.forName(interfaceName);
				o[count] = Class.forName(interfaceImpl).getConstructor(null).newInstance(null);

				++count;
			}

			ImplementationFramework.setInterfaces(c);
			Object obj = ImplementationFramework.getObject(new FIR(), o);
			System.out.println("returning obj");
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
