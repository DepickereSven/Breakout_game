package nu.smashit.core;

// @author Jonas
import nu.smashit.core.bodies.Field;
import nu.smashit.data.Repositories;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;

public class SingleplayerLoop extends GameLoop {

    private int brickHits;
    
    public SingleplayerLoop(Game gm, int level) {
        super(gm, Field.getSingleplayerInstance(
                Repositories.getLevelRepository().getDifficulty(level)
        ));
        this.brickHits = 0;
    }

    @Override
    protected void runLoop(GameStateUpdateAction updateState) {
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
            } else if (runPaddleCollision(updateState)) {
            } else if (runBrickCollision(updateState)) {
                brickHits += 1;
                if (brickHits >= field.getNumberOfNormalBricks()) {
                    gameSession.broadcastAction(new GameVictoryAction());
                    gameSession.stopGame();
                }                
            } else if (Collision.isWallCollision(ball)) {
                ball.inverseHozSpeed();
            }

            ball.move();
        }
    }

}