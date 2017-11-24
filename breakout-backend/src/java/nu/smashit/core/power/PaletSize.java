package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;
import nu.smashit.core.bodies.Paddle;

public class PaletSize extends Power{

    private int newWidth;
    
    public PaletSize(int newWidth){
        this.newWidth = newWidth;
    }

    @Override
    protected void doEffect(GameLoop gameLoop, Player player) {
        player.getPaddle().setWidth(newWidth);
    }

    @Override
    protected void undoEffect(GameLoop gameLoop, Player player) {
        player.getPaddle().setWidth(Paddle.WIDTH);
    }

}
