/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import java.util.TimerTask;
import nu.smashit.socket.actions.GameStateUpdateAction;

/**
 *
 * @author jodus
 */
public class GameLoop extends TimerTask {

    public final Paddle paddle;
    public final Ball ball;
    private final GameSession gameSession;

    public GameLoop(GameSession gm) {
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.gameSession = gm;
    }

    @Override
    public void run() {
        GameStateUpdateAction updateStateAction = new GameStateUpdateAction();

        ball.move(1, 2);
        updateStateAction.addBody(ball);

        paddle.move(3, 0);
        updateStateAction.addBody(paddle);

        gameSession.broadcastAction(updateStateAction);
    }

}
