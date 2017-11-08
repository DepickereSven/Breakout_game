package nu.smashit.core;

// @author Jonas

import java.util.concurrent.ThreadLocalRandom;

public class Field {

    private final int rows;
    private final int cols;
    private Brick[][] field;
    private final int level;
    private boolean multiplayer;
    
    public Field(boolean multiplayer, int level){
        this.rows = 5; //TODO
        this.cols = 5; //TODO
        this.level = validateBetween(level, 1, 100, 1);
        this.multiplayer = multiplayer;
        this.field = new Brick[rows][cols];
        generateField(rows, cols);
        //TODO do something with level
    }
    
    public Field(boolean multiplayer){
        this(multiplayer, 1);
    }
    
    public Brick[][] getField(){
        return field;
    }
    
    public void smashBrick(int x, int y){
        if (getField()[x][y] != null){
            Brick brick = getField()[x][y];
            brick.smashBrick();
            if (brick.isBroken()){
                removeBrick(x, y);
            }
        }
    }
    
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getLevel() {
        return level;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }
    
    private void removeBrick(int x, int y){
        field[x][y] = null;
    }

    private void generateField(int rows, int cols) {
        int numberOfTotalBricks, numberOfNormalBricks, numberOfPowerups, numberOfPowerdowns;
        
        if (isMultiplayer()){
            numberOfTotalBricks = getNumberOfTotalBricksInMultiplayer(rows, cols);
        }else{
            numberOfTotalBricks = rows * cols;
        }
        
        numberOfPowerups = 2; //TODO
        numberOfPowerdowns = 2; //TODO
        numberOfNormalBricks = numberOfTotalBricks - numberOfPowerups - numberOfPowerdowns;
        
        fillField(numberOfNormalBricks, BrickType.BrickSort.NORMAL);
        fillField(numberOfPowerups, BrickType.BrickSort.POWERUP);
        fillField(numberOfPowerdowns, BrickType.BrickSort.POWERDOWN);
    }

    private int getNumberOfTotalBricksInMultiplayer(int rows, int cols) {
        //TODO
        return (int)(rows * cols * 0.5);
    }

    private int getRandomBetween(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private void fillField(int numberOfBricks, BrickType.BrickSort brickSort) {
       for(int blocknr = 0; blocknr < numberOfBricks; blocknr++){
            int x, y;
            do{
                x = getRandomBetween(0,rows-1); //TODO begin en eindwaarden controleren
                y = getRandomBetween(0,cols-1);
            }while(field[x][y] != null);
            
            field[x][y] = createNewBrick(x,y, brickSort);
        }
    }

    private Brick createNewBrick(int x, int y, BrickType.BrickSort brickSort) {
        //TODO
        return new Brick(0, 0, 10,10,new BrickType("test", brickSort));
    }

    @Override
    public String toString() {
        return "Field{" + "rows=" + rows + ", cols=" + cols + ", field=" + showFields() + ", level=" + level + ", multiplayer=" + multiplayer + '}';
    }
    
    private String showFields(){
        StringBuilder sb = new StringBuilder();
        for(int x = 0; x < field.length; x++){
            sb.append("\n");
            for(int y = 0; y < field[x].length; y++){
                if (field[x][y] == null){
                    sb.append(" ..............");
                }else{
                    sb.append(field[x][y].toString());
                }
                
            }
        }
        return sb.toString();
    }

    private int validateBetween(int validate, int min, int max, int defaultvalue) {
        if (validate >= min && validate <= max){
            return validate;
        }else{
            return defaultvalue;
        }
    } 
     
}
