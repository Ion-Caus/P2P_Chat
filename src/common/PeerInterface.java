package common;

import model.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PeerInterface extends Remote {
    void deliverMessage(Message message) throws RemoteException;
}
