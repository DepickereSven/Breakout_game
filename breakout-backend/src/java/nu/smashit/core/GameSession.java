package nu.smashit.core;

import java.util.Timer;
import nu.smashit.socket.Client;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
public abstract class GameSession {

    private final String key;
    protected Player[] players;
    protected GameLoop gameLoop;

    protected final Timer gameLoopTimer;
    protected final int updateInterval;

    GameSession(String key) {
        this.key = key;
        this.gameLoopTimer = new Timer();
        this.updateInterval = 30;
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

    public void stopGame() {
        gameLoopTimer.cancel();

        for (Player p : players) {
            p.client.removeGame();
        }
        GameSessionManager.getInstance().removeGame(key);
    }
    
    public String getKey(){
        return key;
    }
}
