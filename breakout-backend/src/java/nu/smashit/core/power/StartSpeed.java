package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;

public class StartSpeed extends Power {

    private final int newStartSpeed;
    private double originalDx;
    private double originalDy;
    private boolean firstTime;

    public StartSpeed(int powerID, int newStartSpeed) {
        super(powerID);
        this.newStartSpeed = newStartSpeed;
        this.firstTime = true;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (firstTime) {
            firstTime = false;

            originalDx = gameLoop.getBall().getDx();
            originalDy = gameLoop.getBall().getDy();

            gameLoop.getBall().setDx(newStartSpeed / (double) 3);
            gameLoop.getBall().setDy(newStartSpeed);
        }
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        gameLoop.getBall().setDx(originalDx);
        gameLoop.getBall().setDy(originalDy);
    }

}
