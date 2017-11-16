package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public abstract class Power {

    protected final GameLoop gameLoop;
    protected final Player player;
    private int time;
        
    private static final int DURATION = 60;

    
    protected Power(GameLoop gameLoop, Player player){
        this.gameLoop = gameLoop;
        this.player = player;
        time = DURATION;
    }
    
    public void updateEffect(){
        if (timeLeft()){
            doEffect();
        }else{
            undoEffect();
        }
        time--;
    }
    
    protected abstract void doEffect();
    
    protected abstract void undoEffect();
    
    public boolean timeLeft(){
        return time <= 0;
    }
    
}
