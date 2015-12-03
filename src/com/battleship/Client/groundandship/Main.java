package com.battleship.Client.groundandship;

import Server.RemoteService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by nikita on 26.11.2015.
 */

public class Main {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // write your code here
        new Ground();
        Registry registry = LocateRegistry.getRegistry("localhost", 2099);
        RemoteService service = (RemoteService) registry.lookup("sample/HelloService");
        String[] names = { "John", "Jan", "Иван", "Johan", "Hans", "Bill", "Kill" };
        for (String name : names) {
            System.out.println(service.sayHello(name));
        }
    }
}