package nu.smashit.core.bodies;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nu.smashit.core.GameCanvas;

/**
 *
 * @author jodus
 */
@JsonIgnoreProperties(value = {"h"})
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Ball extends MovableBody {

    private double dx;
    private double dy;

    private double dyStartValue = -9;

    public static final int HEIGHT = 12;
    public static final int WIDTH = HEIGHT;

    public static final int Y_START_POS = GameCanvas.HEIGHT - HEIGHT - Paddle.HEIGHT - Paddle.GAP;
    public static final int X_START_POS = GameCanvas.WIDTH / 2;

    static final double MULTIPLIER = 1.0002;

    public Ball() {
        super(X_START_POS, Y_START_POS, WIDTH, HEIGHT);
        this.reset();
    }
    
    public Ball(double speedBall) {
        super(X_START_POS, Y_START_POS, WIDTH, HEIGHT);
        dyStartValue = speedBall;
        setDyStartValue(speedBall);
        this.reset();
    }

    @Override
    public void reset() {
        super.reset();
        setDx(getDxStartValue());
        setDy(getDyStartValue());
    }

    public void move() {
        setDx(getDx() * MULTIPLIER);
        setDy(getDy() * MULTIPLIER);
        super.move((int) getDx(), (int) getDy());
    }

    @JsonIgnore
    public int getRadius() {
        return this.height / 2;
    }

    @JsonIgnore
    public boolean isGoingUp() {
        return getDy() < 0;
    }

    @JsonIgnore
    public boolean isGoingDown() {
        return !isGoingUp();
    }

    @JsonIgnore
    public boolean isGoingLeft() {
        return getDx() < 0;
    }

    @JsonIgnore
    public boolean isGoingRight() {
        return !isGoingLeft();
    }

    public void inverseVerSpeed() {
        setDy(-getDy());
    }

    public void inverseHozSpeed() {
        setDx(-getDx());
    }

    @JsonIgnore
    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    @JsonIgnore
    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    @JsonIgnore
    public double getDyStartValue() {
        return dyStartValue;
    }

    private void setDyStartValue(double dyStartValue) {
        this.dyStartValue = dyStartValue;
    }
    
    @JsonIgnore
    public double getDxStartValue() {
        return getDyStartValue() / 3;
    }

}
