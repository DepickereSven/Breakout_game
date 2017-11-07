/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import java.util.TimerTask;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;
import nu.smashit.socket.actions.OpponentDeathAction;
import nu.smashit.socket.actions.PlayerDeathAction;

/**
 *
 * @author jodus
 */
public class GameLoop extends TimerTask {

    private Ball ball;
    private final GameSession gameSession;

    public GameLoop(GameSession gm) {
        this.gameSession = gm;
        createBall();
    }

    private void createBall() {
        this.ball = new Ball();
    }

    private void removeBall() {
        this.ball = null;
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
                lostPlayer = gameSession.getTopPlayer();
                scoredPlayer = gameSession.getBottomPlayer();
            } else if (ball.isFloorCollision()) {
                lostPlayer = gameSession.getBottomPlayer();
                scoredPlayer = gameSession.getTopPlayer();
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
