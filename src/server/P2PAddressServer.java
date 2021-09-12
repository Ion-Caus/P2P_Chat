package server;

import common.AddressServerInterface;
import model.Peer;
import model.PeerTree;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class P2PAddressServer implements AddressServerInterface {
    private PeerTree peerTree;

    public P2PAddressServer() {
        this.peerTree = new PeerTree();

        try {
            startRegistry();
            startServer();
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void startRegistry() throws RemoteException {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");
        }
        catch (ExportException e) {
            e.printStackTrace();
            System.out.println("Registry already started? " + e.getMessage());
        }
    }

    private void startServer() throws RemoteException, MalformedURLException {
        UnicastRemoteObject.exportObject(this, 0);
        Naming.rebind(SERVICE_NAME, this);
        System.out.println("Address Server started...");
    }

    @Override
    public synchronized boolean registerPeer(Peer peer) throws RemoteException {
        if (!peerTree.exists(peer)) {
            peerTree.add(peer);
            System.out.println("Inserted new peer: " + peer.getAlias());
            return true;
        }
        return false;

    }

    @Override
    public synchronized Peer findPeer(String alias) throws RemoteException {
        System.out.println("Finding " + alias);
        return peerTree.find(alias);
    }
}
