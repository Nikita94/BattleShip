package com.battleship.Client.groundandship;

import java.rmi.RemoteException;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;
import java.util.*;

/**
 * Created by nikita on 03.12.2015.
 */
public class ClientImpl implements ClientBS {
    private Ground ui;
    ClientImpl() {}


   /* @Override
    public String getName() throws RemoteException {
        return name;
    } */

    @Override
    public int sendCell() throws RemoteException {
        return 0;
    }

    public void setGUI(Ground t){
        ui=t ;
    }


    public void tell(String st) throws RemoteException{
        System.out.println(st);
        //ui.writeMsg(st);
    }
}
