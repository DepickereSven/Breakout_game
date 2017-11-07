/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import java.util.TimerTask;

/**
 *
 * @author jodus
 */
public abstract class GameLoop extends TimerTask {

    protected Ball ball;
    protected final GameSession gameSession;

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
    public abstract void run();

}