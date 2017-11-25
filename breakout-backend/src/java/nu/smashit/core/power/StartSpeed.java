package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;

public class StartSpeed extends Power {

    private final int newStartSpeed;
    private double originalDx;
    private double originalDy;

    public StartSpeed(int newStartSpeed) {
        this.newStartSpeed = newStartSpeed;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (originalDx == 0 || originalDy == 0) {
            originalDx = gameLoop.getBall().getDx();
            originalDy = gameLoop.getBall().getDy();
        }
        gameLoop.getBall().setDx(newStartSpeed / (double) 3);
        gameLoop.getBall().setDy(newStartSpeed);
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        gameLoop.getBall().setDx(originalDx);
        gameLoop.getBall().setDy(originalDy);
    }

}
