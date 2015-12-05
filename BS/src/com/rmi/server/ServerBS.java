package com.rmi.server;

import com.rmi.PositionDeck;
import com.rmi.client.Client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.Vector;

/**
 * Created by nikita on 04.12.2015.
 */
public class ServerBS  extends UnicastRemoteObject implements Server{

    private Vector<Client> v=new Vector<>();

    private int currentPosition;
    private ArrayList <PositionDeck> singleDeck = new ArrayList<>();
    private ArrayList <PositionDeck> doubleDeck = new ArrayList<>();
    private ArrayList <PositionDeck> threeDeck = new ArrayList<>();
    private ArrayList <PositionDeck> fourDeck = new ArrayList<>();
    public ServerBS() throws RemoteException{}

    public boolean login(Client a) throws RemoteException{
        System.out.println(a.getName() + "  got connected...." );
        v.add(a);
        return true;
    }

    @Override
    public void sendSingleDeck(ArrayList<PositionDeck> array) throws RemoteException{
        singleDeck = array;
    }

    @Override
    public void sendDoubleDeck(ArrayList<PositionDeck> array) throws RemoteException{
        doubleDeck = array;
    }

    @Override
    public void sendThreeDeck(ArrayList<PositionDeck> array) throws RemoteException{
        threeDeck = array;
    }

    @Override
    public void sendFourDeck(ArrayList<PositionDeck> array) throws RemoteException{
        fourDeck = array;
    }

    @Override
    public void checkPosition(int position, UUID uuid) throws RemoteException {
        int x = position / 10;
        int y = position - x * 10;
        Iterator <PositionDeck> iter = singleDeck.iterator();
        while (iter.hasNext()) {
            PositionDeck p = iter.next();
            if (x == p.getX() && y == p.getY()) {
                iter.remove();
                for (int i = 0; i < v.size(); i++) {
                    Client tmp = (Client) v.get(i);
                    if (!tmp.getUUID().equals(uuid)) {
                        tmp.tell(true, position);
                        //singleDeck.remove(p);
                        int newPosition;
                        for (int j = 1; j < 12; j++) {
                            if (j == 1 || j == 9 || j == 11 || j == 10) {
                                newPosition = position + j;
                                if (newPosition >= 0 && newPosition <= 99  && !(position == 0 && newPosition == 9))
                                    tmp.tell(false, newPosition);
                                newPosition = position - j;
                                if (newPosition >= 0 && newPosition <= 99)
                                    tmp.tell(false, newPosition);
                            }
                        }
                    } else {
                        tmp.tell1(true, position);
                        int newPosition;
                        for (int j = 1; j < 12; j++) {
                            if (j == 1 || j == 9 || j == 11 || j == 10) {
                                newPosition = position + j;
                                if ((newPosition >= 0 && newPosition <= 99) && !(position == 0 && newPosition == 9)) {
                                    System.out.println(newPosition);
                                    tmp.tell1(false, newPosition);
                                }
                                newPosition = position - j;
                                if (newPosition >= 0 && newPosition <= 99) {
                                    System.out.println(newPosition);
                                    tmp.tell1(false, newPosition);
                                }
                            }
                        }
                    }

                }
                return;
            }
        }
        for (PositionDeck p : doubleDeck)
            for(int i=0;i<v.size();i++) {
                if (x == p.getX() && y == p.getY()) {
                    Client tmp = (Client) v.get(i);
                    tmp.tell(true, position);
                    break;
                }
            }
        for (PositionDeck p : threeDeck)
            for(int i=0;i<v.size();i++) {
                if (x == p.getX() && y == p.getY()) {
                    Client tmp = (Client) v.get(i);
                    tmp.tell(true, position);
                    break;
                }
            }
        for (PositionDeck p : fourDeck)
            for(int i=0;i<v.size();i++) {
                if (x == p.getX() && y == p.getY()) {
                    Client tmp = (Client) v.get(i);
                    tmp.tell(true, position);
                    //break;
                }
            }


        for (Client tmp : v)
            if (!tmp.getUUID().equals(uuid))
                tmp.tell(false, position);
            else
                tmp.tell1(false,position);

    }

    /*public void publish(int s) throws RemoteException{
        currentPosition = s;
        for(int i=0;i<v.size();i++){
            try{
                Client tmp=(Client)v.get(i);
                tmp.tell(s);
            }catch(Exception e){
                //problem with the client not connected.
                //Better to remove it
            }
        }
    }*/

    public Vector getConnected() throws RemoteException{
        return v;
    }


}
