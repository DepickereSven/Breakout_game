package nu.smashit.data.dataobjects;

// @author Jonas
public class BrickType {

    public enum BrickSort{
        NORMAL,
        POWERUP,
        POWERDOWN
    }
    
    private String name;
    private BrickSort sort;
    private int brickStrength;
    private int points;
    //TODO extra fields for data powerups and powerdowns

    private static final int DEFAULT_BRICK_STRENGTH = 1;
    private static final int DEFAULT_POINTS = 10;
    
    public BrickType(String name, BrickSort sort){
        this(name, sort, DEFAULT_BRICK_STRENGTH, DEFAULT_POINTS);
    }
    
    public BrickType(String name, BrickSort sort, int brickStrength, int points) {
        this.name = name;
        this.sort = sort;
        this.brickStrength = brickStrength;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public BrickSort getSort() {
        return sort;
    }

    public int getBrickStrength() {
        return brickStrength;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return sort.toString() ;
    }
       
}
