package model;

import common.PeerInterface;

import java.io.Serializable;

public class Peer implements Serializable {
    private String alias;
    private PeerInterface servant;

    public Peer(String alias, PeerInterface servant) {
        this.alias = alias;
        this.servant = servant;
    }

    public String getAlias() {
        return alias;
    }

    public PeerInterface getServant() {
        return servant;
    }
}
