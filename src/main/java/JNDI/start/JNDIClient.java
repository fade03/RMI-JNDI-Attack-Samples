package JNDI.start;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIClient {
    public static void main(String[] args) throws NamingException {
        String url = "rmi://localhost:1099/hello";
        InitialContext context = new InitialContext();
        context.lookup(url);
    }
}
