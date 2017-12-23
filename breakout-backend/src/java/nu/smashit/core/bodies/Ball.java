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

    public static final int Y_START_POS_BOTTOM = GameCanvas.HEIGHT - HEIGHT - Paddle.HEIGHT - Paddle.GAP;
    public static final int Y_START_POS_TOP = HEIGHT + Paddle.HEIGHT + Paddle.GAP;
    public static final int X_START_POS = GameCanvas.WIDTH / 2;

    static final double MULTIPLIER = 1.0002;

    public Ball(int yStartPos) {
        super(X_START_POS, yStartPos, WIDTH, HEIGHT);
        this.reset();
    }

    public Ball(int yStartPos, double speedBall) {
        super(X_START_POS, yStartPos, WIDTH, HEIGHT);
        setDyStartValue(speedBall);
        this.reset();
    }

    public void resetToPos(int yStartPos) {
        this.setY(yStartPos);
    }

    @Override
    public void reset() {
        super.reset();
        this.dx = getDxStartValue();
        this.dy = getDyStartValue();
    }

    public void move() {
        setDx(getDx() * MULTIPLIER);
        setDy(getDy() * MULTIPLIER);
        super.move((int) getDx(), (int) getDy());
    }

    @JsonIgnore
    public int getRadius() {
        return this.getHeight() / 2;
    }

    public void reactToPaddleHit(Paddle paddle) {
        double ballCenterX = getX() + getRadius();
        double paddleCenterX = paddle.getX() + paddle.getWidth() / 2;
        double distanceBetween = Math.sqrt(Math.pow(ballCenterX - paddleCenterX, 2));

        double effect = 0;
        if (distanceBetween > 20) {
            effect = 3.4;
        } else if (distanceBetween > 0) {
            effect = (0.115 * distanceBetween) + 1.1007;
        }

        setDx(effect);
        if (ballCenterX <= paddleCenterX) {
            goLeft();
        } else {
            goRight();
        }
        inverseVerSpeed();
    }

    @JsonIgnore
    public double getDx() {
        return dx;
    }

    @JsonIgnore
    public boolean isGoingLeft() {
        return getDx() < 0;
    }

    @JsonIgnore
    public boolean isGoingRight() {
        return !isGoingLeft();
    }

    @JsonIgnore
    public double getDxStartValue() {
        return getDyStartValue() / 3;
    }

    public void setDx(double dx) {
        this.dx = Math.copySign(dx, getDx());
    }

    public void inverseHorSpeed() {
        this.dx = -getDx();
    }

    public void goLeft() {
        this.dx = -Math.abs(getDx());
    }

    public void goRight() {
        this.dx = Math.abs(getDx());
    }

    @JsonIgnore
    public double getDy() {
        return dy;
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
    public double getDyStartValue() {
        return dyStartValue;
    }

    public void setDy(double dy) {
        this.dy = Math.copySign(dy, getDy());
    }

    public void inverseVerSpeed() {
        this.dy = -this.dy;
    }

    private void setDyStartValue(double dyStartValue) {
        this.dyStartValue = dyStartValue;
    }

}
