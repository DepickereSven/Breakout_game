package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;
import nu.smashit.core.bodies.Ball;

public class BallDiameter extends Power{

    private int newDiameter;
    
    public BallDiameter(int newDiameter){
        this.newDiameter = newDiameter;
    }
    
    @Override
    protected void doEffect(GameLoop gameLoop, Player player) {
        gameLoop.getBall().setHeight(newDiameter);
        gameLoop.getBall().setWidth(newDiameter);
    }

    @Override
    protected void undoEffect(GameLoop gameLoop, Player player) {
        gameLoop.getBall().setHeight(Ball.HEIGHT);
        gameLoop.getBall().setWidth(Ball.WIDTH);
    }

}