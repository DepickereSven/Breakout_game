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

    private int dx;
    private int dy;

    public Ball() {
        super(GameCanvas.WIDTH / 2, GameCanvas.HEIGHT / 2, 14, 14);
        this.dx = 3;
        this.dy = -9;
    }

    public void move() {
        super.move(dx, dy);
    }

    public int getRadius() {
        return this.height / 2;
    }

    public boolean isWallCollision() {
        return x + dx > GameCanvas.WIDTH - getRadius() || x + dx < getRadius();
    }

    public boolean isCeilingCollision() {
        return y + dy < getRadius();
    }

    public boolean isFloorCollision() {
        return y + dy > GameCanvas.HEIGHT - getRadius();
    }

    public void inverseYSpeed() {
        dy = -dy;
    }

    public void inverseXSpeed() {
        dx = -dx;
    }
}
