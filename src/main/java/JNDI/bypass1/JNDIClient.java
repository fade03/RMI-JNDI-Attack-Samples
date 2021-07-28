package JNDI.bypass1;

import javax.naming.InitialContext;

public class JNDIClient {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        context.lookup("ldap://localhost:8888/Calc");
    }
}
