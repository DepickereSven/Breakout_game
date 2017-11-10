package nu.smashit.data.dataobjects;

// @author Jonas
public class Difficulty {

    private int id;
    private int numberPowerups;
    private int numberPowerdowns;
    private int rows;
    private int speed_ball;

    public Difficulty(int id, int numberPowerups, int numberPowerdowns, int rows) {
        setId(id);
        setNumberPowerdowns(numberPowerdowns);
        setNumberPowerups(numberPowerups);
        setRows(rows);
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

    public int getSpeed_ball() {
        return speed_ball;
    }

    private void setSpeed_ball(int speed_ball) {
        this.speed_ball = speed_ball;
    }
    
}