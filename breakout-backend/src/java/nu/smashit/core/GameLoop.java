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

    public final Ball ball;
    private final GameSession gameSession;

    public GameLoop(GameSession gm) {
        ball = new Ball();
        this.gameSession = gm;
    }

    @Override
    public void run() {
        GameStateUpdateAction updateStateAction = new GameStateUpdateAction();

        // Ball movement
        if (ball != null) {
            if (ball.isWallCollision()) {
                ball.inverseXSpeed();
            }
            if (ball.isCeilingCollision()) {
                ball.inverseYSpeed();
            }
            if (ball.isFloorCollision()) {
                ball.inverseYSpeed();
            }

            // Paddle collision
            int pIndex = ball.isGoingUp() ? 1 : 0;
            Paddle p = gameSession.paddles[pIndex];
            if (ball.isCollision(p)) {
                ball.inverseYSpeed();
            }

            ball.move();
            updateStateAction.addBody(ball);
        }

        for (Paddle p : gameSession.paddles) {
            updateStateAction.addBody(p);
        }

        gameSession.broadcastAction(updateStateAction);
    }

}
