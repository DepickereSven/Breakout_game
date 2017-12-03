package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public abstract class Power {

    private int time;
    private static final int DURATION = 500;
    private Player player;

    protected Power(){
        time = DURATION;
        player = null;
    }
    
    public void updateEffect(GameLoop gameLoop){
        if (isActive() && !isLastTime()){
            doEffect(gameLoop);
        }else{
            undoEffect(gameLoop);
        }
        time--;
    }
    
    protected abstract void doEffect(GameLoop gameLoop);
    
    protected abstract void undoEffect(GameLoop gameLoop);
    
    public boolean isActive(){
        return time >= 0;
    }
    
    private boolean isLastTime(){
        return time == 0;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public Player getPlayer(){
        return player;
    }
    
}
