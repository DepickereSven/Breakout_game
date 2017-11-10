package nu.smashit.core.bodies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nu.smashit.core.GameCanvas;

/**
 *
 * @author jodus
 */
public class Ball extends MovableBody {

    public double dx;
    public double dy;

    private double multiplier;

    public static final int HEIGHT = 12;
    public static final int WIDTH = HEIGHT;

    public static final int Y_START_POS = GameCanvas.HEIGHT - HEIGHT - Paddle.HEIGHT - Paddle.GAP;

    public Ball() {
        super(GameCanvas.WIDTH / 2, Y_START_POS, HEIGHT, WIDTH);
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
