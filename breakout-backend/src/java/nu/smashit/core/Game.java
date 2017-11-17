package nu.smashit.core;

import java.util.Timer;
import java.util.TimerTask;
import nu.smashit.socket.Client;
import nu.smashit.socket.actions.GameStartAction;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
public abstract class Game implements Comparable<Game> {

    private final String key;
    protected Player[] players;
    protected GameLoop gameLoop;

    protected final Timer gameLoopTimer;
    protected final Timer countDownTimer;
    protected final int updateInterval;

    public int time;
    public int countDown;
    public int level;

    Game(String key, int level) {
        this.key = key;
        this.gameLoopTimer = new Timer();
        this.countDownTimer = new Timer();
        this.updateInterval = 33;
        this.countDown = 5;
        this.time = 0;
        this.level = level;
    }

    public abstract void join(Client c);

    public abstract Player getPlayer(Client c);

    public void broadcastAction(ResponseAction a) {
        for (Player p : players) {
            p.client.sendAction(a);
        }
    }

    protected abstract void createGameLoop();

    public void startCountDown() {
        gameLoopTimer.scheduleAtFixedRate(gameLoop, 100, updateInterval);

        countDownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (countDown <= 0) {
                    time++;
                } else {
                    countDown--;
                }
            }
        }, 1000, 1000);
    }

    public void startGame() {
        createGameLoop();
        broadcastAction(new GameStartAction());
        gameLoop.initRun();
    }

    public int playerCount() {
        return players.length;
    }

    public void stopGame() {
        countDownTimer.cancel();
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

    @Override
    public int compareTo(Game o) {
        return (this.getKey().compareTo(o.getKey()));
    }

    public void playerReady(Client c) {
        getPlayer(c).ready();

        for (Player p : players) {
            if (!p.isReady()) {
                return;
            }
        }

        startCountDown();
    }

}
