package nu.smashit.data.dataobjects;

// @author Jonas
public class Difficulty {

    private int id;
    private int numberPowerups;
    private int numberPowerdowns;
    private int rows;
    private double speedBall;

    public Difficulty(int id, int numberPowerups, int numberPowerdowns, int rows, double speedBall) {
        setId(id);
        setNumberPowerdowns(numberPowerdowns);
        setNumberPowerups(numberPowerups);
        setRows(rows);
        setSpeedBall(speedBall);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getNumberPowerups() {
        return numberPowerups;
    }

    private void setNumberPowerups(int numberPowerups) {
        this.numberPowerups = numberPowerups;
    }

    public int getNumberPowerdowns() {
        return numberPowerdowns;
    }

    private void setNumberPowerdowns(int numberPowerdowns) {
        this.numberPowerdowns = numberPowerdowns;
    }

    public int getRows() {
        return rows;
    }

    private void setRows(int rows) {
        this.rows = rows;
    }

    public double getSpeedBall() {
        return speedBall;
    }

    private void setSpeedBall(double speedBall) {
        this.speedBall = speedBall;
    }
    
}