package RMI.SAC;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TestInterface extends Remote {
    Object testMethod() throws RemoteException;
}
