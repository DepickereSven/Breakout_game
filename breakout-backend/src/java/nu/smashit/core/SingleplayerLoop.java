package nu.smashit.core;

// @author Jonas
import nu.smashit.core.bodies.Field;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;

public class SingleplayerLoop extends GameLoop {

    public SingleplayerLoop(Game gm) {
        super(gm, Field.getSingleplayerInstance(50));
    }

    @Override
    protected void runLoop(GameStateUpdateAction updateStateAction) {
        // Move paddles to position (Player controls)
        for (Player p : gameSession.players) {
            p.paddle.move();
        }

        // Ball movement
        if (ball != null) {
            if (Collision.isCeilingCollision(ball)) {
                ball.inverseVerSpeed();
            } else if (Collision.isFloorCollision(ball)) {
                gameSession.broadcastAction(new GameLossAction());
                gameSession.stopGame();
                return;
            } else if (runPaddleCollision(updateStateAction)) {
            } else if (runBrickCollision(updateStateAction)) {
            } else if (Collision.isWallCollision(ball)) {
                ball.inverseHozSpeed();
            }

            ball.move();
        }

        gameSession.broadcastAction(updateStateAction);
    }

}
