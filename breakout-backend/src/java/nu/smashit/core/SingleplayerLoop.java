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
        if (getBall() != null) {
            if (Collision.isCeilingCollision(getBall())) {
                getBall().inverseVerSpeed();
            } else if (Collision.isFloorCollision(getBall())) {
                gameEndedLost();
                return;
            } else if (runPaddleCollision(updateState)) {
            } else if (runBrickCollision(updateState)) {
                if (isGameEnd()) {
                    gameEndedWon();
                    return;
                }
            } else if (Collision.isWallCollision(getBall())) {
                getBall().inverseHorSpeed();
            }

            getBall().move();
        }
    }

    private boolean isGameEnd() {
        return getBrickHits() >= getField().getNumberOfTotalBricksInField();
    }

    private void gameEndedWon() {
        getGameSession().broadcastAction(new GameVictoryAction());
        getGameSession().stopGame();
    }

    private void gameEndedLost() {
        getGameSession().broadcastAction(new GameLossAction());
        getGameSession().stopGame();
    }

}
