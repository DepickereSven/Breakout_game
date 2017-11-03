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
    public final Client[] clients;
    public final Paddle[] paddles;
    private GameLoop gameLoop;

    private final Timer gameLoopTimer;
    private final int updateInterval;

    GameSession(String key) {
        this.key = key;
        this.clients = new Client[2];
        this.paddles = new Paddle[2];

        this.gameLoopTimer = new Timer();
        this.updateInterval = 30;
    }

    public void join(Client c) {
        if (clients[1] != null) {
            throw new Error("GameSession full");
        }
        int i = clients[0] == null ? 0 : 1;
        clients[i] = c;

        int paddleY = i == 0 ? GameCanvas.HEIGHT - 30 : 10;
        paddles[i] = new Paddle(paddleY);

        c.setGame(this);
    }

    void broadcastAction(ResponseAction a) {
        for (Client c : clients) {
            try {
                c.sendAction(a);
            } catch (Exception e) {
            }
        }
    }

    public void startGame() {
        broadcastAction(new GameStartAction());

        this.gameLoop = new GameLoop(this);

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

    public Paddle getPaddle(Client c) {
        int index = clients[0] == c ? 0 : 1;
        return paddles[index];
    }
}
