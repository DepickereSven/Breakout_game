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
public abstract class GameLoop extends TimerTask {

    protected Ball ball;
    protected final Field field;
    protected final GameSession gameSession;
    protected Player lastPlayerToHitPaddle;

    protected boolean firstRun;

    public GameLoop(GameSession gm, Field field) {
        this.gameSession = gm;
        this.firstRun = true;
        this.field = field;
        createBall();
    }

    protected void createBall() {
        this.ball = new Ball();
    }

    protected void removeBall() {
        this.ball = null;
    }

    private boolean runBrickRowCollision(int i, GameStateUpdateAction updateStateAction) {
        BrickRow brickRow = field.getRow(i);

        if (Collision.isVerCollision(ball, brickRow)) {
            for (Brick b : brickRow.bricks) {
                if (b != null && !b.isBroken() && Collision.isHozCollision(ball, b)) {
                    b.smashBrick();
                    updateStateAction.addBrick(b);

                    if (Collision.isTopOrBottomCollision(ball, b)) {
                        ball.inverseVerSpeed();
                    } else {
                        ball.inverseHozSpeed();
                    }

                    if (lastPlayerToHitPaddle != null) {
                        lastPlayerToHitPaddle.score.addBrickSmash();
                    }

                    return true;
                }
            }
        }

        return false;
    }

    protected boolean runBrickCollision(GameStateUpdateAction updateStateAction) {
        int start = 0;
        int end = field.getNumberOfRows() - 1;

        if (ball.isGoingUp()) {
            for (int i = end; i >= start; i--) {
                if (runBrickRowCollision(i, updateStateAction)) {
                    return true;
                }
            }
        } else {
            for (int i = start; i <= end; i++) {
                if (runBrickRowCollision(i, updateStateAction)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected boolean runPaddleCollision(GameStateUpdateAction updateStateAction) {
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

    @Override
    public abstract void run();

}
