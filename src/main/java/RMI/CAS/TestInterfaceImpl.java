package RMI.CAS;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TestInterfaceImpl extends UnicastRemoteObject implements TestInterface {
    protected TestInterfaceImpl() throws RemoteException {
        super();
    }

    @Override
    public void testMethod(Object obj) throws RemoteException {
        System.out.println("...");
    }
}
