package nu.smashit.core;

// @author Jonas

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import nu.smashit.utils.Tools;

public class Field {
  
    private final int numberOfRows;
    private final BrickRow[] field;
    private final int level;
    private boolean multiplayer;
                
    private static final int BLOCK_HEIGHT = 35;
    
    public Field(boolean multiplayer, int level){
        this.multiplayer = multiplayer;
        this.level = Tools.validateBetween(level, 1, 100, 1);
        this.numberOfRows = calculateNumberOfRows();
        this.field = new BrickRow[numberOfRows];
        generateField();
    }
    
    public Field(boolean multiplayer){
        this(multiplayer, 1);
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
            int y = (BLOCK_HEIGHT * rownr) + 50;
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
        for(int x = 0; x < field.length; x++){
            sb.append(field[x].toString());
            sb.append("\n");
        }
        return sb.toString();
    }
     
    public class BrickRow{
        private Brick[] row;
        private int numberOfNormalBricks;
        private int numberOfPowerups;
        private int numberOfPowerdowns;
        private int numberOfEmptyPlaces;
        private int y;
        
        public BrickRow(int numberOfNormalBricks, int numberOfPowerups, int numberOfPowerdowns, int numberOfEmptyPlaces, int y ){
            this.numberOfNormalBricks = Tools.validateBetween(numberOfNormalBricks, 1, 10, 6);
            this.numberOfPowerups = Tools.validateBetween(numberOfPowerdowns, 0, 3, 0);
            this.numberOfPowerdowns = Tools.validateBetween(numberOfPowerdowns, 0, 3, 0);
            this.numberOfEmptyPlaces = Tools.validateBetween(numberOfEmptyPlaces, 0, 10, 0);
            this.y = y;

            row = new Brick[getNumberOfTotalPlaces()];
            fillRow();
        }

        public Brick getBrick(int colIndex) {
            return row[colIndex];
        }
        
        public void removeBrick(int colIndex){
            row[colIndex] = null;
        }
        
        public int getNumberOfTotalPlaces(){
            return getNumberOfEmptyPlaces() + getNumberOfTotalBricks();
        }

        public int getNumberOfTotalBricks() {
            return numberOfNormalBricks + numberOfPowerups + numberOfPowerdowns;
        }

        public int getNumberOfNormalBricks() {
            return numberOfNormalBricks;
        }

        public int getNumberOfPowerups() {
            return numberOfPowerups;
        }

        public int getNumberOfPowerdowns() {
            return numberOfPowerdowns;
        }
        
        public int getNumberOfEmptyPlaces(){
            return numberOfEmptyPlaces;
        }
        
        private void fillRow(){
           fillType(numberOfNormalBricks, BrickType.BrickSort.NORMAL);
           fillType(numberOfPowerups, BrickType.BrickSort.POWERUP);
           fillType(numberOfPowerdowns, BrickType.BrickSort.POWERDOWN);
        }
        
        private void fillType(int number, BrickType.BrickSort sort){
            int block_width = (int) (GameCanvas.WIDTH / getNumberOfTotalPlaces());
            int place;
            
            for (int bricknr = 0; bricknr < number; bricknr++){
                do{
                   place = Tools.getRandomBetween(0, getNumberOfTotalPlaces() -1);
                }while(row[place] != null);
                
                int x = place * block_width;
                row[place] = new Brick(x,y,block_width,BLOCK_HEIGHT,getRandomBrickType(sort));
            }
        }

        private BrickType getRandomBrickType(BrickType.BrickSort sort) {
            //TODO uit db halen
            return new BrickType("test", sort);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for(int y = 0; y < row.length; y++){
                if (row[y] == null){
                    sb.append("*.............*");
                }else{
                    sb.append(row[y].toString());
                }
            }
            return sb.toString();
        }
    }
     
}
