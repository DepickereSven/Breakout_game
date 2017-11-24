package nu.smashit.core.power;

// @author Jonas

import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public class NoPower extends Power{
    
    public NoPower(){}

    @Override
    protected void doEffect(GameLoop gameLoop, Player player) {}

    @Override
    protected void undoEffect(GameLoop gameLoop, Player player) {}

}