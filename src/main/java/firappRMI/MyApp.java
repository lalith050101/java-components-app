package firappRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyApp extends Remote {

	public Object getObject() throws RemoteException;
}
