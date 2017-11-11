package nu.smashit.core;

import java.util.Timer;
import nu.smashit.socket.Client;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
public abstract class Game {

    private final String key;
    protected Player[] players;
    protected GameLoop gameLoop;

    protected final Timer gameLoopTimer;
    protected final int updateInterval;
    protected final int startDelay;

    Game(String key) {
        this.key = key;
        this.gameLoopTimer = new Timer();
        this.updateInterval = 30;
        this.startDelay = 2000;
    }

    public abstract void join(Client c);

    public abstract Player getPlayer(Client c);

    public void broadcastAction(ResponseAction a) {
        for (Player p : players) {
            try {
                p.client.sendAction(a);
            } catch (Exception e) {
            }
        }
    }

    public abstract void startGame();

    public int playerCount() {
        return players.length;
    }

    public void stopGame() {
        gameLoopTimer.cancel();

        for (Player p : players) {
            p.client.removeGame();
        }
        GameManager.getInstance().removeGame(key);
    }

    public String getKey() {
        return key;
    }

    public boolean isFull() {
        for (Player p : players) {
            if (p == null) {
                return false;
            }
        }
        return true;
    }
}
