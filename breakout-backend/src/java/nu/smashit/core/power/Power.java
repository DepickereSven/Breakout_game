package nu.smashit.core.power;

// @author Jonas
import java.util.HashSet;
import java.util.Set;
import nu.smashit.core.GameLoop;
import nu.smashit.core.Player;

public abstract class Power {

    public final int powerID;
    private int time;
    private static final int DURATION = 500;
    protected static final int TRANSITION = 25;
    public final boolean multiplePlayers;
    private final Set<Player> players;

    protected Power(int powerID, boolean multiplePlayers) {
        this.powerID = powerID;
        time = DURATION;
        this.multiplePlayers = multiplePlayers;
        players = new HashSet<>();
    }

    public void updateEffect(GameLoop gameLoop) {
        if (isActive() && !isLastTime()) {
            doEffect(gameLoop);
        } else {
            undoEffect(gameLoop);
        }
        time--;
    }

    protected abstract void doEffect(GameLoop gameLoop);

    protected abstract void undoEffect(GameLoop gameLoop);

    public boolean isActive() {
        return time >= 0;
    }
    
    protected boolean isEndTransition(){
        return time <= TRANSITION;
    }
    
    protected boolean isBeginTransition(){
        return DURATION - time <= TRANSITION;
    }

    private boolean isLastTime() {
        return time == 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Player getPlayer() {
        return players.iterator().next();
    }

    public boolean containsPlayer(Player p) {
        return players.contains(p);
    }

}
