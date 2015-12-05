package com.rmi.server;

import java.rmi.Naming;

public class Main {

    public static void main(String[] args) {
        try {
            //System.setSecurityManager(new RMISecurityManager());
            java.rmi.registry.LocateRegistry.createRegistry(1099);

            Server b=new ServerBS();
            Naming.rebind("rmi://localhost/RMIBS", b);
            System.out.println("Good");
            System.out.println("[System] Chat Server is ready.");
        }catch (Exception e) {
            System.out.println("Chat Server failed: " + e);
        }
    }
}
