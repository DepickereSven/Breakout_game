package nu.smashit.core;

import nu.smashit.core.bodies.Field;
import nu.smashit.core.bodies.BrickRow;
import nu.smashit.core.bodies.Brick;
import nu.smashit.core.bodies.Ball;
import java.util.TimerTask;
import nu.smashit.socket.actions.GameStateUpdateAction;

/**
 *
 * @author jodus
 */
public abstract class GameLoop extends TimerTask {

    protected Ball ball;
    protected final Field field;
    protected final Game gameSession;
    protected Player lastPlayerToHitPaddle;
    private double runCount;

    protected boolean firstRun;

    public GameLoop(Game gm, Field field) {
        this.gameSession = gm;
        this.field = field;
        this.runCount = 0;
        createBall();
    }

    protected void createBall() {
        this.ball = new Ball();
    }

    protected void removeBall() {
        this.ball = null;
    }

    private boolean runBrickRowCollision(int i, GameStateUpdateAction updateState) {
        BrickRow brickRow = field.getRow(i);

        if (Collision.isVerCollision(ball, brickRow)) {
            for (Brick brick : brickRow.bricks) {
                if (brick != null && !brick.isBroken() && Collision.isHozCollision(ball, brick)) {
                    brick.smashBrick();
                    updateState.addBrick(brick);

                    if (Collision.isTopOrBottomCollision(ball, brick)) {
                        ball.inverseVerSpeed();
                    } else {
                        ball.inverseHozSpeed();
                    }

                    if (lastPlayerToHitPaddle != null) {
                        lastPlayerToHitPaddle.score.addBrickSmash(brick);
                    }

                    return true;
                }
            }
        }

        return false;
    }

    protected boolean runBrickCollision(GameStateUpdateAction updateState) {
        int start = 0;
        int end = field.getNumberOfRows() - 1;

        if (ball.isGoingUp()) {
            for (int i = end; i >= start; i--) {
                if (runBrickRowCollision(i, updateState)) {
                    return true;
                }
            }
        } else {
            for (int i = start; i <= end; i++) {
                if (runBrickRowCollision(i, updateState)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean runPaddleCollision(GameStateUpdateAction updateState) {
        int pIndex = (ball.isGoingUp() && gameSession.playerCount() > 1) ? 1 : 0;
        Player player = gameSession.players[pIndex];

        if (player != null && Collision.isCollision(ball, player.paddle)) {
            if (Collision.isTopOrBottomCollision(ball, player.paddle)) {
                ball.inverseVerSpeed();
            } else {
                ball.inverseHozSpeed();
            }
            lastPlayerToHitPaddle = player;
            return true;
        }

        return false;
    }

    private void reverseYBodies(GameStateUpdateAction updateState) {
        for (Player p : updateState.players) {
            p.paddle.reverseY();
        }
        for (Brick b : updateState.bricks) {
            b.reverseY();
        }
        updateState.ball.reverseY();
    }

    @Override
    public void run() {
        GameStateUpdateAction updateState = new GameStateUpdateAction(ball, gameSession.players, gameSession.countDown, gameSession.time);

        if (gameSession.countDown > 0) {
            if (runCount < 2) {
                for (BrickRow br : field.brickRows) {
                    for (Brick b : br.bricks) {
                        if (b != null) {
                            updateState.addBrick(b);
                        }
                    }
                }
            }
        } else {
            runLoop(updateState);
        }

        if (gameSession.playerCount() > 1) {
            MultiplayerGame mg = (MultiplayerGame) gameSession;

            mg.getBottomPlayer().client.sendAction(updateState);
            reverseYBodies(updateState);
            mg.getTopPlayer().client.sendAction(updateState);
            reverseYBodies(updateState);
        } else {
            gameSession.broadcastAction(updateState);
        }

        runCount++;
    }

    protected abstract void runLoop(GameStateUpdateAction updateState);
}
