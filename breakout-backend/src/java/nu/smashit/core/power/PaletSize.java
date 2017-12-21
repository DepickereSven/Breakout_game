package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;
import nu.smashit.core.bodies.Paddle;

public class PaletSize extends Power {

    private int newWidth;

    public PaletSize(int powerID, int newWidth) {
        super(powerID, false);
        this.newWidth = newWidth;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (!getPlayers().isEmpty()) {
            getPlayer().getPaddle().setWidth(newWidth);
        }
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        if (!getPlayers().isEmpty()) {
            getPlayer().getPaddle().setWidth(Paddle.WIDTH);
        }
    }

}
