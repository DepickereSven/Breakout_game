package nu.smashit.core;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.actions.GameStartAction;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
public abstract class Game implements Comparable<Game> {

    private final String key;
    private final Timer gameLoopTimer;
    private final Timer countDownTimer;
    private final int updateInterval;
    private final int level;

    private Player[] players;
    private GameLoop gameLoop;
    private int time;
    private int countDown;

    Game(String key, int level) {
        this.key = key;
        this.gameLoopTimer = new Timer();
        this.countDownTimer = new Timer();
        this.updateInterval = 30;
        this.countDown = 5;
        this.time = 0;
        this.level = level;
    }

    public abstract void join(User u);

    public abstract Player getPlayer(User u);

    public void broadcastAction(ResponseAction a) {
        for (Player p : getPlayers()) {
            p.sendAction(a);
        }
    }

    protected abstract void createGameLoop();

    public void startCountDown() {
        getGameLoopTimer().scheduleAtFixedRate(getGameLoop(), 100, getUpdateInterval());

        getCountDownTimer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (getCountDown() <= 0) {
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
        getGameLoop().initRun();
    }

    public int playerCount() {
        return getPlayers().length;
    }

    public void stopGame() {
        getCountDownTimer().cancel();
        getGameLoopTimer().cancel();

        for (Player p : getPlayers()) {
            if (p != null) {
                p.getUser().removeGame();
            }
        }
        GameManager.getInstance().removeGame(getKey());
    }

    public String getKey() {
        return key;
    }

    public boolean isFull() {
        for (Player p : getPlayers()) {
            if (p == null) {
                return false;
            }
        }
        return true;
    }

    public void playerReady(User u) {
        getPlayer(u).ready();

        for (int i = 0; i < getPlayers().length; i++) {
            Player p = getPlayers()[i];
            if (p == null || !p.isReady()) {
                return;
            }
        }

        startCountDown();
    }

    public int getTime() {
        return time;
    }

    public int getCountDown() {
        return countDown;
    }

    public int getLevel() {
        return level;
    }

    public Player[] getPlayers() {
        return players;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public Timer getGameLoopTimer() {
        return gameLoopTimer;
    }

    public Timer getCountDownTimer() {
        return countDownTimer;
    }

    public int getUpdateInterval() {
        return updateInterval;
    }

    protected void setPlayers(Player[] players) {
        this.players = players;
    }

    protected void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    @Override
    public int compareTo(Game o) {
        return (this.getKey().compareTo(o.getKey()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.key);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

}
