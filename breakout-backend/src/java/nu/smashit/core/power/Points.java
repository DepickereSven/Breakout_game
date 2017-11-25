package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;

public class Points extends Power{
    
    private int pointsFactor;
    
    public Points(int pointsFactor){
        this.pointsFactor = pointsFactor;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (getPlayer() != null){
            getPlayer().getScore().setPointsFactor(pointsFactor);
        } 
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        if (getPlayer() != null){
            getPlayer().getScore().setPointsFactor(1);
        }
    }

}
