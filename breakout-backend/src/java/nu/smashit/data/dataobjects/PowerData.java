package nu.smashit.data.dataobjects;

// @author Jonas
public class PowerData {

    private final int powerID;
    private String type;
    private double value;

    public PowerData(int powerID, String type, double value) {
        this.powerID = powerID;
        this.type = type;
        this.value = value;
    }

    public int getPowerID() {
        return powerID;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

}
