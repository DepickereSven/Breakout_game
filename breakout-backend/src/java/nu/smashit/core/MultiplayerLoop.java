package nu.smashit.core;

// @author Jonas
import nu.smashit.core.bodies.Field;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;
import nu.smashit.socket.actions.OpponentDeathAction;
import nu.smashit.socket.actions.PlayerDeathAction;

public class MultiplayerLoop extends GameLoop {

    public MultiplayerLoop(MultiplayerGame gm) {
        super(gm, Field.getMultiplayerInstance());
    }

    @Override
    protected void runLoop(GameStateUpdateAction updateState) {
        // Ball movement
        if (getBall() != null) {
            Player scoredPlayer = null;
            Player lostPlayer = null;

            if (Collision.isCeilingCollision(getBall())) {
                lostPlayer = ((MultiplayerGame) getGameSession()).getTopPlayer();
                scoredPlayer = ((MultiplayerGame) getGameSession()).getBottomPlayer();
            } else if (Collision.isFloorCollision(getBall())) {
                lostPlayer = ((MultiplayerGame) getGameSession()).getBottomPlayer();
                scoredPlayer = ((MultiplayerGame) getGameSession()).getTopPlayer();
            } else if (runPaddleCollision(updateState)) {
            } else if (runBrickCollision(updateState)) {
            } else if (Collision.isWallCollision(getBall())) {
                getBall().inverseHozSpeed();
            }

            if (scoredPlayer != null && lostPlayer != null) {
                lostPlayer.getScore().subtractDeath();
                scoredPlayer.getScore().addOpponentDeath();

                if (!lostPlayer.getScore().isAlive()) {
                    scoredPlayer.sendAction(new GameVictoryAction());
                    lostPlayer.sendAction(new GameLossAction());
                    getGameSession().stopGame();
                    return;
                }

                scoredPlayer.sendAction(new OpponentDeathAction());
                lostPlayer.sendAction(new PlayerDeathAction());

                getBall().reset();
            }

            getBall().move();
        }
    }

}
