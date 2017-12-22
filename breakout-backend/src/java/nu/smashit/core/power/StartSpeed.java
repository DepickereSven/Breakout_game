package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;

public class StartSpeed extends Power {

    private final int newStartSpeed;
    private double originalDy;
    private boolean firstTime;

    public StartSpeed(int powerID, int newStartSpeed) {
        super(powerID, true);
        this.newStartSpeed = newStartSpeed;
        this.firstTime = true;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (firstTime) {
            firstTime = false;
            originalDy = gameLoop.getBall().getDy();
            gameLoop.getBall().setDy(newStartSpeed);
        }
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        gameLoop.getBall().setDy(originalDy);
    }

}
