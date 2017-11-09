package nu.smashit.core;

// @author Jonas
import nu.smashit.utils.Tools;

public class Field {
  
    private final int numberOfRows;
    private final BrickRow[] field;
    private final int level;
    private final boolean multiplayer;
                
    private static final int MARGIN_TOP = 50;

    public static Field getSingleplayerInstance(int level) {
        return new Field(false, level);
    }

    public static Field getMultiplayerInstance() {
        return new Field(true, 1);
    }

    private Field(boolean multiplayer, int level) {
        this.multiplayer = multiplayer;
        this.level = Tools.validateBetween(level, 1, 100, 1);
        this.numberOfRows = calculateNumberOfRows();
        this.field = new BrickRow[numberOfRows];
        generateField();
    }
    
    public BrickRow[] getField(){
        return field;
    }
    
    public BrickRow getRow(int rowIndex){
        return field[rowIndex];
    }
    
    public Brick getBrick(int rowIndex, int colIndex){
        return getRow(rowIndex).getBrick(colIndex);
    }
    
    private void removeBrick(int rowIndex, int colIndex){
        getRow(rowIndex).removeBrick(colIndex);
    }
    
    public void smashBrick(int rowIndex, int colIndex){
        if (getBrick(rowIndex, colIndex) != null){
            Brick brick = getBrick(rowIndex, colIndex);
            brick.smashBrick();
            if (brick.isBroken()){
                removeBrick(rowIndex, colIndex);
            }
        }
    }
    
    public int getRows() {
        return numberOfRows;
    }

    public int getLevel() {
        return level;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }
    
    public boolean isSingleplayer(){
        return !multiplayer;
    }
    
    private void generateField() {
        int[] powerupsPerRow = new int[numberOfRows];
        powerupsPerRow[0] = 1;
        powerupsPerRow[1] = 1;
        powerupsPerRow[2] = 1;
        powerupsPerRow = Tools.shuffleArray(powerupsPerRow);
        
        int[] powerdownsPerRow = Tools.shuffleArray(powerupsPerRow.clone());
        
        for (int rownr = 0; rownr < numberOfRows; rownr++){
            int y = (Brick.HEIGHT * rownr) + MARGIN_TOP;
            int numberOfTotalPlacesInRow = Tools.getRandomBetween(4, 8);
            int numberOfPowerupsInRow = powerupsPerRow[rownr];
            int numberOfPowerdownsInRow = powerdownsPerRow[rownr];
            int numberOfEmptyPlacesInRow = getNumberOfEmptyPlacesInRow(numberOfTotalPlacesInRow);
            int numberOfNormalBricksInRow = numberOfTotalPlacesInRow - numberOfPowerupsInRow - numberOfPowerdownsInRow - numberOfEmptyPlacesInRow;
            
            field[rownr] = new BrickRow(numberOfNormalBricksInRow, numberOfPowerupsInRow, numberOfPowerdownsInRow, numberOfEmptyPlacesInRow, y);
        }
    }

    private int calculateNumberOfRows() {
        //TODO read parameters from database
        if (isMultiplayer()){
            return 6;
        }
        
        if (isSingleplayer()){
            if (level <= 2){
                return 3;
            }else if (level > 2 && level <= 4){
                return 4;
            }else if (level > 4 && level <= 10){
                return 5;
            }else if (level > 10 && level <= 40){
                return 6;
            }else if (level > 40 && level <= 75){
                return 7;
            }else{
                return 8;
            }
        }
        
        throw new Error("Error in field");
    }
    
    private int getNumberOfEmptyPlacesInRow(int numberOfTotalPlacesInRow) {
        if (isMultiplayer()){
            return (int)(numberOfTotalPlacesInRow * 0.45);
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isMultiplayer() == true ? "Multiplayer" : "Singleplayer").append("\n");
        for (BrickRow field1 : field) {
            sb.append(field1.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
