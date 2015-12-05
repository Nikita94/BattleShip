package com.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

/**
 * Created by nikita on 04.12.2015.
 */
public class ClientBS  extends UnicastRemoteObject implements Client{


    UUID uniqueKey;
    private String name;
    private Ground ui;
    public ClientBS (String n) throws RemoteException {
        name=n;
        uniqueKey = UUID.randomUUID();
    }

    @Override
    public void tell1(boolean hit, int position) throws RemoteException {
       ui.updateSquare2(position, hit);
    }

    @Override
    public void tell(boolean hit, int position) throws RemoteException {
        ui.updateSquare(position, hit);
    }

    /*public void tell(String st) throws RemoteException{
            System.out.println(st);
           // ui.writeMsg(st);
        } */
    public String getName() throws RemoteException{
        return name;
    }

    public void setGUI(Ground t){
        ui=t ;
    }

    public UUID getUUID() throws RemoteException{
         return uniqueKey;
    }
}
