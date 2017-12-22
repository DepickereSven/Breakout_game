package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;
import nu.smashit.core.bodies.Paddle;

public class PaletSize extends Power {

    private int newWidth;
    private final int difPerTime;

    public PaletSize(int powerID, int newWidth) {
        super(powerID, false);
        this.newWidth = newWidth;
        int difference = newWidth - Paddle.WIDTH;
        this.difPerTime = difference / TRANSITION;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (!getPlayers().isEmpty()) {
            Paddle paddle = getPlayer().getPaddle();
            int currentW = paddle.getWidth();
            int currentX = paddle.getX();
            if (isBeginTransition()){
                paddle.setX(currentX - (difPerTime /2));
                paddle.setWidth(currentW + difPerTime);               
            }else if (isEndTransition()) {
                paddle.setX(currentX + (difPerTime /2));
                paddle.setWidth(currentW - difPerTime);
            }else{
                paddle.setWidth(newWidth);
            }
        }
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        if (!getPlayers().isEmpty()) {
            getPlayer().getPaddle().setWidth(Paddle.WIDTH);
        }
    }

}
