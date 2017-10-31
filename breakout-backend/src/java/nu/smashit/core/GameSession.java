package nu.smashit.core;

import java.util.Timer;
import nu.smashit.socket.Client;
import nu.smashit.socket.actions.GameStartAction;
import nu.smashit.socket.actions.GameStopAction;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
public class GameSession {

    private final Client[] clients;
    private final Paddle paddle;
    private final String key;
    private final Timer timer;

    GameSession(String key) {
        this.key = key;
        this.clients = new Client[2];
        this.paddle = new Paddle();

        this.timer = new Timer();
    }

    public void join(Client c) {
        if (clients[1] != null) {
            throw new Error("GameSession full");
        }
        int i = clients[0] == null ? 0 : 1;
        clients[i] = c;
        c.setGame(this);
    }

    private void broadcastAction(ResponseAction a) {
        for (Client c : clients) {
            c.sendAction(a);
        }
    }

    public void startGame() {
        broadcastAction(new GameStartAction());
    }

    public void stopGame() {
        broadcastAction(new GameStopAction());

        for (Client c : clients) {
            c.removeGame();
        }
        GameSessionManager.getInstance().removeGame(getKey());
    }

    public String getKey() {
        return key;
    }

    public Timer getTimer() {
        return timer;
    }
    
}
