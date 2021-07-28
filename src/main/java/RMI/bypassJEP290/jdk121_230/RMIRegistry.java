package RMI.bypassJEP290.jdk121_230;

import java.rmi.registry.LocateRegistry;

public class RMIRegistry {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry Start");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (true) ;
    }
}
