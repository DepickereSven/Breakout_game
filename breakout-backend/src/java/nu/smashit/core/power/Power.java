package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public abstract class Power {

    private int time;
    private static final int DURATION = 60;

    
    protected Power(){
        time = DURATION;
    }
    
    public void updateEffect(GameLoop gameLoop, Player player){
        if (timeLeft()){
            doEffect(gameLoop, player);
        }else{
            undoEffect(gameLoop, player);
        }
        time--;
    }
    
    protected abstract void doEffect(GameLoop gameLoop, Player player);
    
    protected abstract void undoEffect(GameLoop gameLoop, Player player);
    
    public boolean timeLeft(){
        return time <= 0;
    }
    
}
