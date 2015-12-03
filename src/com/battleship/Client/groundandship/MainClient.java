package com.battleship.Client.groundandship;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by nikita on 26.11.2015.
 */

public class MainClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        // write your code here
        new Ground();
    }
}