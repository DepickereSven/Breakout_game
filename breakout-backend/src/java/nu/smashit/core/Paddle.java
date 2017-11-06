/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

/**
 *
 * @author jodus
 */
public class Paddle extends MovableBody {

    public final static int MOVE_STEP_SIZE = 9;

    public final static int GAP = 10;

    public final static int HEIGHT = 14;
    public final static int WIDTH = 50;

    public final static int PLAYER_1_Y = GameCanvas.HEIGHT - HEIGHT - GAP;
    public final static int PLAYER_2_Y = GAP;

    public Paddle(int y) {
        super(0, y, WIDTH, HEIGHT);
    }

    public void moveLeft() {
        move(-MOVE_STEP_SIZE, 0);
    }

    public void moveRight() {
        move(MOVE_STEP_SIZE, 0);
    }
}
