package nu.smashit.socket.actions;

/**
 *
 * @author jodus
 */
public class GameVictoryAction implements ResponseAction {

    public int points;
    
    public GameVictoryAction(){
        this(0);
    }
    
    public GameVictoryAction(int points){
        this.points = points;
    }
    
}
