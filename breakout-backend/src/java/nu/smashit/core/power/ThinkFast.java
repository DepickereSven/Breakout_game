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
        this.originalDx = gameLoop.getBall().getDx();
        this.originalDy = gameLoop.getBall().getDy();
    }

    @Override
    protected void doEffect() {
        gameLoop.getBall().setDx( originalDx + extraSpeed );
        gameLoop.getBall().setDy( originalDy + extraSpeed );
    }

    @Override
    protected void undoEffect() {
        gameLoop.getBall().setDx( originalDx );
        gameLoop.getBall().setDy( originalDy );
    }

}
