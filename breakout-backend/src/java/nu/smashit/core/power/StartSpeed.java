package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public class StartSpeed extends Power {

    private final int newStartSpeed;
    private double originalDx;
    private double originalDy;

    public StartSpeed(int newStartSpeed) {
        this.newStartSpeed = newStartSpeed;
    }

    @Override
    protected void doEffect(GameLoop gameLoop, Player player) {
        if (originalDx == 0 || originalDy == 0) {
            originalDx = gameLoop.getBall().getDx();
            originalDy = gameLoop.getBall().getDy();
        }
        gameLoop.getBall().setDx(newStartSpeed);
        gameLoop.getBall().setDy(newStartSpeed);
    }

    @Override
    protected void undoEffect(GameLoop gameLoop, Player player) {
        gameLoop.getBall().setDx(originalDx);
        gameLoop.getBall().setDy(originalDy);
    }

}
