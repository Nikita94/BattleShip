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
    private ArrayList <PositionDeck> doubleDeck;
    private ArrayList <PositionDeck> threeDeck = new ArrayList<>();
    private ArrayList <PositionDeck> fourDeck = new ArrayList<>();
    private ArrayList <PositionDeck> neighborDoubleDeck;
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
        doubleDeck  = new ArrayList<>(array);
        neighborDoubleDeck = new ArrayList<>(array);
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
        int secondPosition = 0;
        Iterator <PositionDeck> iter = singleDeck.iterator();
        while (iter.hasNext()) {
            PositionDeck p = iter.next();
            if (x == p.getX() && y == p.getY()) {
                //iter.remove();
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
                                   // System.out.println(newPosition);
                                    tmp.tell1(false, newPosition);
                                }
                                newPosition = position - j;
                                if (newPosition >= 0 && newPosition <= 99) {
                                   // System.out.println(newPosition);
                                    tmp.tell1(false, newPosition);
                                }
                            }
                        }
                    }

                }
                return;
            }
        }
        iter = doubleDeck.iterator();
        while (iter.hasNext()) {
            PositionDeck p = iter.next();
            if (x == p.getX() && y == p.getY()) {
                iter.remove();
                for (int i = 0; i < v.size(); i++) {
                    Client tmp = (Client) v.get(i);
                    boolean flag = false;
                    for (PositionDeck neighbor : doubleDeck) {
                        //System.out.println("x = " + neighbor.getX() + " y = " + (neighbor.getY() + 1));
                        if ((x + 1) == neighbor.getX() && y == neighbor.getY() || (x - 1) == neighbor.getX() && y == neighbor.getY()
                                || x == neighbor.getX() && y == neighbor.getY() + 1 || x == neighbor.getX() && y == neighbor.getY() - 1) {
                            flag = true;
                        }

                    }
                    PositionDeck firstNei = null, secondNei = null;
                    if (flag == false) {
                        int k = 0;
                        Iterator<PositionDeck> neiiter = neighborDoubleDeck.iterator();
                        while (neiiter.hasNext()) {
                            PositionDeck newDecks = neiiter.next();
                            if (!doubleDeck.contains(newDecks)) {
                                if (k == 0) {
                                    firstNei = new PositionDeck(newDecks.getX(), newDecks.getY());
                                    k = k + 1;
                                    neiiter.remove();
                                } else {
                                    secondNei = new PositionDeck(newDecks.getX(), newDecks.getY());
                                    neiiter.remove();
                                }
                            }
                        }
                        if (firstNei != null && secondNei != null) {
                            position = firstNei.getX() * 10 + firstNei.getY();
                            secondPosition = secondNei.getX() * 10 + secondNei.getY();
                        }
                    }
                    if (!tmp.getUUID().equals(uuid)) {
                        tmp.tell(true, position);
                        int newPosition, newPosition2;
                        if (flag == false) {
                                for (int j = 1; j < 12; j++) {
                                    if (j == 1 || j == 9 || j == 11 || j == 10){
                                        newPosition = position + j;
                                        newPosition2 = secondPosition + j;
                                        if ((newPosition >= 0 && newPosition <= 99) && (newPosition2 >= 0 && newPosition2 <= 99)) {
                                            tmp.tell(false, newPosition);
                                            tmp.tell(false, newPosition2);
                                        }
                                        newPosition = position - j;
                                        newPosition2 = secondPosition - j;
                                        if ((newPosition >= 0 && newPosition <= 99) && (newPosition2 >= 0 && newPosition2 <= 99)) {
                                            tmp.tell(false, newPosition);
                                            tmp.tell(false, newPosition2);
                                        }
                                    }

                            }
                        }

                    } else {
                        tmp.tell1(true, position);
                        int newPosition, newPosition2;
                        if (flag == false) {
                                for (int j = 1; j < 12; j++) {
                                    if (j == 1 || j == 9 || j == 11 || j == 10){
                                        newPosition = position + j;
                                        newPosition2 = secondPosition + j;
                                        if ((newPosition >= 0 && newPosition <= 99) && (newPosition2 >= 0 && newPosition2 <= 99)) {
                                            tmp.tell1(false, newPosition);
                                            tmp.tell1(false, newPosition2);
                                        }
                                        newPosition = position - j;
                                        newPosition2 = secondPosition - j;
                                        if ((newPosition >= 0 && newPosition <= 99) && (newPosition2 >= 0 && newPosition2 <= 99)) {
                                            tmp.tell1(false, newPosition);
                                            tmp.tell1(false, newPosition2);
                                        }
                                    }
                                }

                        }

                    }

                }
                return;
            }
        }
        for (PositionDeck p : threeDeck)
            for (int i = 0; i < v.size(); i++) {
                Client tmp = (Client) v.get(i);
                if (!tmp.getUUID().equals(uuid)) {
                    tmp.tell(true, position);
                }
                else tmp.tell1(true, position);
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
