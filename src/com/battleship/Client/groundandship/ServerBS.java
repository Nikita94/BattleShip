package com.battleship.Client.groundandship;

import Server.*;
import Server.ClientBS;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nikita on 03.12.2015.
 */
public interface ServerBS extends Remote {

    public boolean login(ClientImpl a) throws RemoteException;

}
