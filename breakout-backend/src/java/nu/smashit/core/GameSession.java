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

    public final String key;
    public final Player[] players;
    private GameLoop gameLoop;

    private final Timer gameLoopTimer;
    private final int updateInterval;

    GameSession(String key) {
        this.key = key;
        this.players = new Player[2];

        this.gameLoopTimer = new Timer();
        this.updateInterval = 30;
    }

    public void join(Client c) {
        if (players[1] != null) {
            throw new Error("GameSession full");
        }
        int i = players[0] == null ? 0 : 1;
        String playerType = i == 0 ? Player.PLAYER_1 : Player.PLAYER_2;
        players[i] = new Player(c, playerType);

        c.setGame(this);
    }

    void broadcastAction(ResponseAction a) {
        for (Player p : players) {
            try {
                p.client.sendAction(a);
            } catch (Exception e) {
            }
        }
    }

    public Player getPlayer(Client c) {
        return players[0].client == c ? players[0] : players[1];
    }

    public void startGame() {
        broadcastAction(new GameStartAction());

        this.gameLoop = new GameLoop(this);

        gameLoopTimer.scheduleAtFixedRate(this.gameLoop, 0, updateInterval);
    }

    public void stopGame() {
        gameLoopTimer.cancel();

        broadcastAction(new GameStopAction());

        for (Player p : players) {
            p.client.removeGame();
        }
        GameSessionManager.getInstance().removeGame(key);
    }
}
