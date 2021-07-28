package RMI.CAS;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestInterface extends Remote {
    void testMethod(Object obj) throws RemoteException;
}
