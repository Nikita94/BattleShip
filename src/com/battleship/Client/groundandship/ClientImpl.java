package com.battleship.Client.groundandship;

import java.rmi.RemoteException;

/**
 * Created by nikita on 03.12.2015.
 */
public class ClientImpl implements ClientBS {
    private String name;
    ClientImpl(String name) { this.name = name; }


    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public int sendCell() throws RemoteException {
        return 0;
    }
}
