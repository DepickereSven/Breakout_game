package nu.smashit.data.dataobjects;

// @author Jonas
public class BrickType {

    public enum Type {
        N, U, D
    }

    private String name;
    private int brickPoints;
    private int brickStrength;
    private Type type;
    private PowerData power;

    private static final int DEFAULT_BRICK_STRENGTH = 1;
    private static final int DEFAULT_POINTS = 10;

    public BrickType(String name, Type type) {
        this(name, type, DEFAULT_POINTS, DEFAULT_BRICK_STRENGTH);
    }

    public BrickType(String name, Type type, int brickPoints, int brickStrength) {
        this(name, type, brickPoints, brickStrength, null);
    }

    public BrickType(String name, Type type, int brickPoints, int brickStrength, PowerData power) {
        this.name = name;
        this.type = type;
        this.brickPoints = brickPoints;
        this.brickStrength = brickStrength;
        this.power = power;
    }
    
    public String getName() {
        return name;
    }

    public int getBrickPoints() {
        return brickPoints;
    }

    public int getBrickStrength() {
        return brickStrength;
    }

    public Type getType() {
        return type;
    }

    public PowerData getPowerData() {
        return power;
    }
    
    @Override
    public String toString() {
        return getType().toString();
    }
    
}