package nu.smashit.socket;

import nu.smashit.socket.actions.ResponseAction;
import javax.websocket.Session;
import nu.smashit.core.Game;

/**
 *
 * @author jodus
 */
public class Client {

    private final Session session;
    private Game gameSession;

    public Client(Session session) {
        this.session = session;
    }

    public String getId() {
        return session.getId();
    }

    public void sendAction(ResponseAction a) {
        session.getAsyncRemote().sendObject(a);
    }

    public boolean isInGame() {
        return gameSession != null;
    }

    public void setGame(Game gameSession) {
        this.gameSession = gameSession;
    }

    public Game getGame() {
        return gameSession;
    }

    public void removeGame() {
        this.gameSession = null;
    }

    @Override
    public String toString() {
        return getId();
    }
}
