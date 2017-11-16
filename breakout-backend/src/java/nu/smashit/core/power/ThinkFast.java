package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public class ThinkFast extends Power{

    private final int extraSpeed;
    private final double originalDx;
    private final double originalDy;
    
    public ThinkFast(GameLoop gameLoop, Player player,int extraSpeed){
        super(gameLoop, player);
        this.extraSpeed = extraSpeed;
        this.originalDx = gameLoop.ball.dx;
        this.originalDy = gameLoop.ball.dy;
    }

    @Override
    protected void doEffect() {
        gameLoop.ball.dx = originalDx + extraSpeed;
        gameLoop.ball.dy = originalDy + extraSpeed;
    }

    @Override
    protected void undoEffect() {
        gameLoop.ball.dx = originalDx;
        gameLoop.ball.dy = originalDy;
    }

}
