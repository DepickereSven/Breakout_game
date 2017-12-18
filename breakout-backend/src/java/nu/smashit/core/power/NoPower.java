package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;

public class NoPower extends Power{
    
    public NoPower(){
        super(0);
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {}

    @Override
    protected void undoEffect(GameLoop gameLoop) {}

}