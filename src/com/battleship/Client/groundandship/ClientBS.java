package com.battleship.Client.groundandship;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nikita on 03.12.2015.
 */
public interface ClientBS extends Remote {
    //public String getName() throws RemoteException;
    public int sendCell() throws RemoteException;
    public void tell (String name)throws RemoteException ;

}
