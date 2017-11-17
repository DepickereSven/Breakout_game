package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;
import nu.smashit.core.bodies.Ball;

public class SchrunkenBall extends Power{

    private static final int SMALLER_DIAMETER = 8;
    
    public SchrunkenBall(GameLoop gameLoop, Player player){
        super(gameLoop, player);
    }
    
    @Override
    protected void doEffect() {
        gameLoop.getBall().setHeight(SMALLER_DIAMETER);
        gameLoop.getBall().setWidth( SMALLER_DIAMETER);
    }

    @Override
    protected void undoEffect() {
        gameLoop.getBall().setHeight(Ball.HEIGHT);
        gameLoop.getBall().setWidth(Ball.WIDTH);
    }

}