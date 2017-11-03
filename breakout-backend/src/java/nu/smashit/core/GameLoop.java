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
            int dx = ball.dx;
            int dy = ball.dy;

            // Wall collission
            if (ball.x + dx > GameCanvas.WIDTH - ball.getRadius() || ball.x + dx < ball.getRadius()) {
                dx = -dx;

                // Ceiling collission
            } else if (ball.y + dy < ball.getRadius()) {
                dy = -dy;

                // Floor collission
            } else if (ball.y + dy > GameCanvas.HEIGHT - ball.getRadius()) {
                dy = -dy;
            }
            // Paddle collision
            for (Paddle p : gameSession.paddles) {
                if (p.isCollision(ball)) {
                    dy = -dy;
                    break;
                }
            }


            ball.move(dx, dy);

            updateStateAction.addBody(ball);
        }

        for (Paddle p : gameSession.paddles) {
            updateStateAction.addBody(p);

        }

        gameSession.broadcastAction(updateStateAction);
    }

}
