package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;
import nu.smashit.core.bodies.Ball;

public class BallDiameter extends Power {

    private int newDiameter;

    public BallDiameter(int powerID, int newDiameter) {
        super(powerID, true);
        this.newDiameter = newDiameter;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        gameLoop.getBall().setHeight(newDiameter);
        gameLoop.getBall().setWidth(newDiameter);
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        gameLoop.getBall().setHeight(Ball.HEIGHT);
        gameLoop.getBall().setWidth(Ball.WIDTH);
    }

}
