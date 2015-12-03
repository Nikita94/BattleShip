package Server;


import java.rmi.Naming;

/**
 * Created by nikita on 26.11.2015.
 */
public class MainServer {

    public static void main(String... args) {
        try {
            //System.setSecurityManager(new RMISecurityManager());
            java.rmi.registry.LocateRegistry.createRegistry(1099);

            ServerBS b=new ServerImpl();
            Naming.rebind("localhost", b);
            System.out.println("[System] Chat Server is ready.");
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }
}
