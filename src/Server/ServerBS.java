package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nikita on 03.12.2015.
 */
public interface ServerBS extends Remote {

    public boolean login(ClientBS a) throws RemoteException;
}