package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by nikita on 03.12.2015.
 */
public class ServerImpl extends UnicastRemoteObject implements ServerBS {


    public ServerImpl() throws RemoteException {}
}
