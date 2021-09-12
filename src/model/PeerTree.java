package model;

import java.util.TreeMap;

public class PeerTree {
    private TreeMap<String, Peer> list;

    public PeerTree() {
        this.list = new TreeMap<>();
    }

    public boolean exists(Peer peer) {
        return list.containsKey(peer.getAlias());
    }

    public void add(Peer peer) {
        list.put(peer.getAlias(), peer);
    }

    public Peer find(String alias) {
        return list.get(alias);
    }
}
