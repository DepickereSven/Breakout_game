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

    public static int MOVE_STEP_SIZE = 9;

    public Paddle(int y) {
        super(0, y, 50, 14);
    }

    public void moveLeft() {
        move(-MOVE_STEP_SIZE, 0);
    }

    public void moveRight() {
        move(MOVE_STEP_SIZE, 0);
    }
}
