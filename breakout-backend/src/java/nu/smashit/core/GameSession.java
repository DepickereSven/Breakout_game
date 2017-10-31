package nu.smashit.core;

import java.util.Timer;
import java.util.TimerTask;
import nu.smashit.socket.Client;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameStartAction;
import nu.smashit.socket.actions.GameStopAction;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
public class GameSession {

    private final Client[] clients;
    private final String key;
    private final Timer timer;
    private final int updateInterval;

    public final Paddle paddle;
    public final Ball ball;

    GameSession(String key) {
        this.key = key;
        this.clients = new Client[2];
        this.timer = new Timer();
        this.updateInterval = 33;

        this.paddle = new Paddle();
        this.ball = new Ball();
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

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runUpdate();
            }
        }, 0, updateInterval);
    }

    public void stopGame() {
        timer.cancel();

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

    public void runUpdate() {
        GameStateUpdateAction updateStateAction = new GameStateUpdateAction();

        ball.move(1, 2);
        updateStateAction.addBody(ball);

        paddle.move(3, 0);
        updateStateAction.addBody(paddle);

        broadcastAction(updateStateAction);
    }
}
