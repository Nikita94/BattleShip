package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by nikita on 03.12.2015.
 */
public class ServerImpl extends UnicastRemoteObject implements ServerBS {


    public ServerImpl() throws RemoteException {}

    public boolean login(ClientBS a) throws RemoteException{
        System.out.println(a.getName() + "  got connected....");
        a.tell("You have Connected successfully.");
        //publish(a.getName()+ " has just connected.");
        //v.add(a);
        return true;
    }
}
