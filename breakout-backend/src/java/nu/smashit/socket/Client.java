package nu.smashit.socket;

import nu.smashit.socket.actions.ResponseAction;
import javax.websocket.Session;
import nu.smashit.core.GameSession;

/**
 *
 * @author jodus
 */
public class Client {

    private final Session session;
    private GameSession gameSession;

    Client(Session session) {
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

    public void setGame(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public GameSession getGame() {
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
