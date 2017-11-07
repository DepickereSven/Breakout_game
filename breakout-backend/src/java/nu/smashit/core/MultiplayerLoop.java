package nu.smashit.core;

// @author Jonas

import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;
import nu.smashit.socket.actions.OpponentDeathAction;
import nu.smashit.socket.actions.PlayerDeathAction;

public class MultiplayerLoop extends GameLoop{

    public MultiplayerLoop(MultiplayerSession gm) {
        super(gm);
    }
    
    @Override
    public void run() {
        GameStateUpdateAction updateStateAction = new GameStateUpdateAction(gameSession.players);

        for (Player p : gameSession.players) {
            p.paddle.move();
        }

        // Ball movement
        if (ball != null) {
            Player scoredPlayer = null;
            Player lostPlayer = null;

            if (ball.isWallCollision()) {
                ball.inverseHozSpeed();
            } else if (ball.isCeilingCollision()) {
                lostPlayer = ((MultiplayerSession) gameSession).getTopPlayer();
                scoredPlayer = ((MultiplayerSession) gameSession).getBottomPlayer();
            } else if (ball.isFloorCollision()) {
                lostPlayer = ((MultiplayerSession) gameSession).getBottomPlayer();
                scoredPlayer = ((MultiplayerSession) gameSession).getTopPlayer();
            } else {
                // Paddle collision
                int pIndex = ball.isGoingUp() ? 1 : 0;
                Player player = gameSession.players[pIndex];
                if (ball.isCollision(player.paddle)) {
                    if (ball.isVerCollision(player.paddle)) {
                        ball.inverseVerSpeed();
                    } else {
                        ball.inverseHozSpeed();
                    }
                }
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
            updateStateAction.addBody(ball);
        }

        gameSession.broadcastAction(updateStateAction);
    }

}
