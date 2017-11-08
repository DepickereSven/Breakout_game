package nu.smashit.core;

// @author Jonas
public class Brick extends Body{

    private BrickType type;
    private int remainingSmashesNeeded;
    
    public Brick(int x, int y, int width, int height, BrickType type) {
        super(x, y, width, height);
        this.type = type;
        this.remainingSmashesNeeded = type.getBrickStrength();
    }

    public BrickType getType() {
        return type;
    }

    public int getRemainingSmashesNeeded() {
        return remainingSmashesNeeded;
    }
    
    public boolean smashBrick(){
        remainingSmashesNeeded --;
        return isBroken();       
    }
    
    public boolean isBroken(){
        return remainingSmashesNeeded < 1;
    }

    @Override
    public String toString() {
        return "Brick" + type + "(" + remainingSmashesNeeded + ")";
    }
    
}