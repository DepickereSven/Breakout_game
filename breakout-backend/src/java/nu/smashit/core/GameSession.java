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

    private final String key;
    private final Client[] clients;
    private final GameLoop gameLoop;

    private final Timer gameLoopTimer;
    private final int updateInterval;

    GameSession(String key) {
        this.key = key;
        this.clients = new Client[2];
        this.gameLoop = new GameLoop(this);

        this.gameLoopTimer = new Timer();
        this.updateInterval = 30;
    }

    public void join(Client c) {
        if (clients[1] != null) {
            throw new Error("GameSession full");
        }
        int i = clients[0] == null ? 0 : 1;
        clients[i] = c;
        c.setGame(this);
    }

    void broadcastAction(ResponseAction a) {
        for (Client c : clients) {
            c.sendAction(a);
        }
    }

    public void startGame() {
        broadcastAction(new GameStartAction());

        gameLoopTimer.scheduleAtFixedRate(this.gameLoop, 0, updateInterval);
    }

    public void stopGame() {
        gameLoopTimer.cancel();

        broadcastAction(new GameStopAction());

        for (Client c : clients) {
            c.removeGame();
        }
        GameSessionManager.getInstance().removeGame(getKey());
    }

    public String getKey() {
        return key;
    }
}
