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
        this.originalDx = gameLoop.ball.getDx();
        this.originalDy = gameLoop.ball.getDy();
    }

    @Override
    protected void doEffect() {
        gameLoop.ball.setDx( originalDx + extraSpeed );
        gameLoop.ball.setDy( originalDy + extraSpeed );
    }

    @Override
    protected void undoEffect() {
        gameLoop.ball.setDx( originalDx );
        gameLoop.ball.setDy( originalDy );
    }

}
