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
public class Ball extends MovableBody {

    public int dx;
    public int dy;

    public Ball() {
        super(GameCanvas.WIDTH / 2, GameCanvas.HEIGHT / 2, 15, 15);
        this.dx = 1;
        this.dy = -7;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        this.dx = dx;
        this.dy = dy;
    }
}
