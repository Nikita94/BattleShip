package com.rmi.server;

import com.rmi.PositionDeck;
import com.rmi.client.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

/**
 * Created by nikita on 04.12.2015.
 */
public interface Server extends Remote {
   // public void publish (int s)throws RemoteException ;
    public Vector getConnected() throws RemoteException ;
    public boolean login(Client a) throws RemoteException;
    public void sendSingleDeck(ArrayList<PositionDeck> array) throws RemoteException;
    public void sendDoubleDeck(ArrayList<PositionDeck> array) throws RemoteException;
    public void sendThreeDeck(ArrayList<PositionDeck> array) throws RemoteException;
    public void sendFourDeck(ArrayList<PositionDeck> array) throws RemoteException;
    public void checkPosition (int position, UUID uuid) throws RemoteException;



}
