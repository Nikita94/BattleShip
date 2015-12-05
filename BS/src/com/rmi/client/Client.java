package com.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
import java.util.Vector;

/**
 * Created by nikita on 04.12.2015.
 */
public interface Client extends Remote {
    public static final long serialVersionUID = 1179227415408023062L;
    public void tell (boolean hit, int position)throws RemoteException;
    public String getName()throws RemoteException ;
    public UUID getUUID() throws RemoteException;;
    public void tell1(boolean hit, int position) throws RemoteException;
}