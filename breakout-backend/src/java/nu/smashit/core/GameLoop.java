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

        // Ball movement
        if (ball != null) {
            int dx = ball.dx;
            int dy = ball.dy;

            // Wall collission
            if (ball.x + dx > GameCanvas.WIDTH - ball.height / 2 || ball.x + dx < ball.height / 2) {
                dx = -dx;

                // Ceiling collission
            } else if (ball.y + dy < ball.height / 2) {
                dy = -dy;

                // Floor collission
            } else if (ball.y + dy > GameCanvas.HEIGHT - ball.height / 2) {
                dy = -dy;
            }

            ball.move(dx, dy);

            updateStateAction.addBody(ball);
        }

        paddle.move(0, 0);
        updateStateAction.addBody(paddle);

        gameSession.broadcastAction(updateStateAction);
    }

}
