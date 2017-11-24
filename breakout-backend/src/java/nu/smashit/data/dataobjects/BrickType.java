package nu.smashit.data.dataobjects;

// @author Jonas
public class BrickType {

    public enum BrickSort {
        N, U, D
    }

    private int brickTypeID;
    private int brickStrength;
    private String name;
    private BrickSort type;
    private String subType;
    private int points;
    private int value;

    private static final int DEFAULT_BRICK_STRENGTH = 1;
    private static final int DEFAULT_POINTS = 10;

    public BrickType(String name, BrickSort sort, String subType) {
        this(0, name, sort, DEFAULT_BRICK_STRENGTH, DEFAULT_POINTS, 0, subType);
    }

    public BrickType(String name, BrickSort sort, int brickStrength, int points, String subType) {
        this(0, name, sort, brickStrength, points, 0, subType);
    }

    public BrickType(int brickTypeID, String name, BrickSort type, int brickStrength, int points, int value, String subType) {
        this.brickTypeID = brickTypeID;
        this.brickStrength = brickStrength;
        this.name = name;
        this.type = type;
        this.subType = subType;
        this.points = points;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public BrickSort getType() {
        return type;
    }

    public int getBrickStrength() {
        return brickStrength;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public int getBrickTypeID() {
        return brickTypeID;
    }

    public int getValue() {
        return value;
    }

    public String getSubType() {
        return subType;
    }

}
