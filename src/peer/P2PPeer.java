package peer;

import common.AddressServerInterface;
import common.PeerInterface;
import model.Message;
import model.Peer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class P2PPeer implements PeerInterface {
    private AddressServerInterface server;
    private Peer self;

    public P2PPeer(String alias) {
        try {
            UnicastRemoteObject.exportObject(this, 0);
            self = new Peer(alias, this);
            server = (AddressServerInterface) Naming.lookup("rmi://localhost:" + AddressServerInterface.SERVICE_PORT + "/" + AddressServerInterface.SERVICE_NAME);
            server.registerPeer(self);

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void send(String toAlias, String text) {
        try {
            Peer receiver = findReceiver(toAlias);
            if (receiver == null) {
                System.out.println("ERROR - " + toAlias + " cannot be found.");
                return;
            }

            Message msg = new Message(text, self, receiver);
            receiver.getServant().deliverMessage(msg);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Peer findReceiver(String alias) throws RemoteException {
        return server.findPeer(alias);
    }

    @Override
    public void deliverMessage(Message message) throws RemoteException {
        System.out.print("\n--> @" + message.getFrom().getAlias() + ": " + message.getText() + "\n--> ");
    }
}
