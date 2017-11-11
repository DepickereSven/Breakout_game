package nu.smashit.data.dataobjects;

// @author Jonas
public class BrickType {

    public enum BrickSort{ N, U, D }
    
    private int brickTypeID;
    private String name;
    private BrickSort sort;
    private int brickStrength;
    private int points;
    private int value;
    //TODO extra fields for data powerups and powerdowns?

    private static final int DEFAULT_BRICK_STRENGTH = 1;
    private static final int DEFAULT_POINTS = 10;
    
    public BrickType(String name, BrickSort sort){
        this(0,name, sort, DEFAULT_BRICK_STRENGTH, DEFAULT_POINTS,0);
    }
    
    public BrickType(String name, BrickSort sort, int brickStrength, int points) {
        this(0,name, sort, brickStrength, points,0);
    }
    
    public BrickType(int brickTypeID, String name, BrickSort sort, int brickStrength, int points, int value) {
        this.brickTypeID = brickTypeID;
        this.name = name;
        this.sort = sort;
        this.brickStrength = brickStrength;
        this.points = points;
        this.value = value;
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

    public int getBrickTypeID() {
        return brickTypeID;
    }

    public int getValue() {
        return value;
    }
       
}
