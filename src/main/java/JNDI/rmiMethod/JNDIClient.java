package JNDI.rmiMethod;

import javax.naming.InitialContext;

public class JNDIClient {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        context.lookup("rmi://localhost:1099/calc");
    }
}
