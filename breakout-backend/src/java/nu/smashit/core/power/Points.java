package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public class Points extends Power{
    
    private int pointsFactor;
    
    public Points(int pointsFactor){
        this.pointsFactor = pointsFactor;
    }

    @Override
    protected void doEffect(GameLoop gameLoop, Player player) {
        player.getScore().setPointsFactor(pointsFactor);
    }

    @Override
    protected void undoEffect(GameLoop gameLoop, Player player) {
        player.getScore().setPointsFactor(1);
    }

}
