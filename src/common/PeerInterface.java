package common;

import model.Message;

public interface PeerInterface {
    void deliverMessage(Message message);
}
