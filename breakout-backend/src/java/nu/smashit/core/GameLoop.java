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
        GameStateUpdateAction updateStateAction = new GameStateUpdateAction(gameSession.players);

        // Ball movement
        if (ball != null) {
            if (ball.isWallCollision()) {
                ball.inverseHozSpeed();
            }
            if (ball.isCeilingCollision()) {
                ball.inverseVerSpeed();
            }
            if (ball.isFloorCollision()) {
                ball.inverseVerSpeed();
            }

            // Paddle collision
            int pIndex = ball.isGoingUp() ? 1 : 0;
            Paddle p = gameSession.players[pIndex].paddle;
            if (ball.isCollision(p)) {
                if (ball.isVerCollision(p)) {
                    ball.inverseVerSpeed();
                } else {
                    ball.inverseHozSpeed();
                }
            }

            ball.move();
            updateStateAction.addBody(ball);
        }

        gameSession.broadcastAction(updateStateAction);
    }

}
