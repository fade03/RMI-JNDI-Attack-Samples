package RMI.SAC;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry(1099);
        TestInterface remote = (TestInterface) registry.lookup("test");
        System.out.println(remote.testMethod());
    }
}
