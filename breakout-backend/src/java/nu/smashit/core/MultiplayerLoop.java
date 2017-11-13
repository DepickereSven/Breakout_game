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

        for (Player p : gameSession.players) {
            p.paddle.move();
        }

        // Ball movement
        if (ball != null) {
            Player scoredPlayer = null;
            Player lostPlayer = null;

            if (Collision.isCeilingCollision(ball)) {
                lostPlayer = ((MultiplayerGame) gameSession).getTopPlayer();
                scoredPlayer = ((MultiplayerGame) gameSession).getBottomPlayer();
            } else if (Collision.isFloorCollision(ball)) {
                lostPlayer = ((MultiplayerGame) gameSession).getBottomPlayer();
                scoredPlayer = ((MultiplayerGame) gameSession).getTopPlayer();
            } else if (runPaddleCollision(updateState)) {
            } else if (runBrickCollision(updateState)) {
            } else if (Collision.isWallCollision(ball)) {
                ball.inverseHozSpeed();
            }

            if (scoredPlayer != null && lostPlayer != null) {
                lostPlayer.score.subtractDeath();
                scoredPlayer.score.addOpponentDeath();

                if (!lostPlayer.score.isAlive()) {
                    scoredPlayer.client.sendAction(new GameVictoryAction());
                    lostPlayer.client.sendAction(new GameLossAction());
                    gameSession.stopGame();
                    return;
                }

                scoredPlayer.client.sendAction(new OpponentDeathAction());
                lostPlayer.client.sendAction(new PlayerDeathAction());

                ball.reset();
            }

            ball.move();
        }
    }

}
