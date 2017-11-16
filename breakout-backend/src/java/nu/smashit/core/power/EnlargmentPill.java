package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;
import nu.smashit.core.bodies.Paddle;

public class EnlargmentPill extends Power{

    private int extraWidth;
    
    public EnlargmentPill(GameLoop gameLoop, Player player, int extraWidth){
        super(gameLoop, player);
        this.extraWidth = extraWidth;
    }

    @Override
    protected void doEffect() {
        player.paddle.width = Paddle.WIDTH + extraWidth;
    }

    @Override
    protected void undoEffect() {
        player.paddle.width = Paddle.WIDTH;
    }

}
