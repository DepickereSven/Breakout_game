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

    private double dx;
    private double dy;

    private final double multiplier;

    public Ball() {
        super(GameCanvas.WIDTH / 2, GameCanvas.HEIGHT / 2, 12, 12);
        this.dx = 3;
        this.dy = -9;
        this.multiplier = 1.0002;
    }

    public void move() {
        dx = dx * multiplier;
        dy = dy * multiplier;
        super.move((int) dx, (int) dy);
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
