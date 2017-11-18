package nu.smashit.socket;

import nu.smashit.socket.actions.ResponseAction;
import javax.websocket.Session;
import nu.smashit.data.dataobjects.User;

/**
 *
 * @author jodus
 */
public class Client {

    private final Session session;
    private User user;

    public Client(Session session) {
        this.session = session;
    }

    public String getId() {
        return session.getId();
    }

    public String getShortId() {
        return getId().substring(0, 5);
    }

    public void sendAction(ResponseAction a) {
        if (session != null && session.isOpen()) {
            session.getAsyncRemote().sendObject(a);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
}