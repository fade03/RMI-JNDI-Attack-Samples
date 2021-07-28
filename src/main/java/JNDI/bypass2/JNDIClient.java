package JNDI.bypass2;

import javax.el.ELProcessor;
import javax.naming.InitialContext;

public class JNDIClient {
    public static void main(String[] args) throws Exception {
        String url = "rmi://localhost:1099/pwn";
        InitialContext context = new InitialContext();
        context.lookup(url);
    }
}
