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

    final int DY_START_VALUE = -9;
    final int DX_START_VALUE = DY_START_VALUE / 3;

    public static final int HEIGHT = 12;
    public static final int WIDTH = HEIGHT;

    public static final int Y_START_POS = GameCanvas.HEIGHT - HEIGHT - Paddle.HEIGHT - Paddle.GAP;
    public static final int X_START_POS = GameCanvas.WIDTH / 2;

    static final double MULTIPLIER =  1.0002;

    public Ball() {
        super(X_START_POS, Y_START_POS, HEIGHT, WIDTH);
        this.reset();
    }

    @Override
    public void reset() {
        super.reset();
        this.dx = DX_START_VALUE;
        this.dy = DY_START_VALUE;
    }

    public void move() {
        dx = dx * MULTIPLIER;
        dy = dy * MULTIPLIER;
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
    public boolean isGoingDown() {
        return !isGoingUp();
    }

    @JsonIgnore
    public boolean isGoingLeft() {
        return dx < 0;
    }
    
    @JsonIgnore
    public boolean isGoingRight() {
        return !isGoingLeft();
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
