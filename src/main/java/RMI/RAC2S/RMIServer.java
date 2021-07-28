package RMI.RAC2S;

import RMI.SAC.TestInterfaceImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry(1099);
        TestInterfaceImpl testInterface = new TestInterfaceImpl();
        registry.bind("test", testInterface);
    }
}
