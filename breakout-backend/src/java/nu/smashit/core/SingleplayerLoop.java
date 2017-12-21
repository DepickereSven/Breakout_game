package nu.smashit.core;

// @author Jonas
import nu.smashit.core.bodies.Field;
import nu.smashit.data.Repositories;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;

public class SingleplayerLoop extends GameLoop {

    public SingleplayerLoop(Game gm, int level) {
        super(gm, Field.getSingleplayerInstance(
                Repositories.getLevelRepository().getDifficulty(level)
        ));
    }

    @Override
    protected void runLoop(GameStateUpdateAction updateState) {
        // Ball movement
        if (getBall() != null) {
            if (Collision.isCeilingCollision(getBall())) {
                getBall().inverseVerSpeed();
            } else if (Collision.isFloorCollision(getBall())) {
                getGameSession().broadcastAction(new GameLossAction());
                getGameSession().stopGame();
                return;
            } else if (runPaddleCollision(updateState)) {
            } else if (runBrickCollision(updateState)) {
                if (getBrickHits() >= getField().getNumberOfTotalBricksInField()) {
                    getGameSession().broadcastAction(new GameVictoryAction());
                    getGameSession().stopGame();
                }
            } else if (Collision.isWallCollision(getBall())) {
                getBall().inverseHorSpeed();
            }

            getBall().move();
        }
    }

}
