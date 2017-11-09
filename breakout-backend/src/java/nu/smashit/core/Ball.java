/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author jodus
 */
public class Ball extends MovableBody {

    public double dx;
    public double dy;

    private double multiplier;

    public Ball() {
        super(GameCanvas.WIDTH / 2, (int) (GameCanvas.HEIGHT * 0.8), 12, 12);
        this.reset();
    }

    @Override
    public void reset() {
        super.reset();
        this.dx = -3;
        this.dy = -9;
        this.multiplier = 1.0002;
    }

    public void move() {
        dx = dx * multiplier;
        dy = dy * multiplier;
        super.move((int) dx, (int) dy);
    }

    @JsonIgnore
    public int getRadius() {
        return this.height / 2;
    }

    @JsonIgnore
    public boolean isGoingUp() {
        return dy < 0;
    }

    @JsonIgnore
    public boolean isGoingLeft() {
        return dx < 0;
    }

    @JsonIgnore
    public void inverseVerSpeed() {
        dy = -dy;
    }

    @JsonIgnore
    public void inverseHozSpeed() {
        dx = -dx;
    }
}
