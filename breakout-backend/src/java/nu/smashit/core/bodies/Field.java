package nu.smashit.core.bodies;

// @author Jonas
import nu.smashit.core.GameCanvas;
import nu.smashit.data.dataobjects.Difficulty;
import nu.smashit.utils.Tools;

public class Field extends Body {

    private final BrickRow[] brickRows;
    private final int numberOfRows;
    private final Difficulty difficulty;
    private final boolean multiplayer;
    private int numberOfTotalBricksInField;

    private static final int SINGLEPLAYER_MARGIN_TOP = 50;
    private static final int[] ALLOWED_BRICKS_IN_ROW = new int[]{4, 5, 8};

    public static Field getSingleplayerInstance(Difficulty difficulty) {
        return new Field(false, difficulty);
    }

    public static Field getMultiplayerInstance() {
        return new Field(true, new Difficulty(0, 3, 3, 6, -9));
    }

    private Field(boolean multiplayer, Difficulty difficulty) {
        super(0, 0, GameCanvas.WIDTH, 0);

        this.multiplayer = multiplayer;
        this.difficulty = difficulty;
        this.numberOfRows = difficulty.getRows();
        this.brickRows = new BrickRow[numberOfRows];
        this.numberOfTotalBricksInField = 0;

        super.setHeight(calcFieldHeight());
        super.setY(calcMarginTop() + super.getHeight());

        generateField();
    }

    private int calcFieldHeight() {
        return numberOfRows * Brick.HEIGHT;
    }

    private int calcMarginTop() {
        if (multiplayer) {
            return Math.round((GameCanvas.HEIGHT - calcFieldHeight()) / 2) - Brick.HEIGHT;
        }
        return SINGLEPLAYER_MARGIN_TOP;
    }

    private void generateField() {

        int[] powerupsPerRow = getPowersPerRow(difficulty.getNumberPowerups());
        int[] powerdownsPerRow = getPowersPerRow(difficulty.getNumberPowerdowns());

        for (int rownr = 0; rownr < numberOfRows; rownr++) {
            int y = (Brick.HEIGHT * (rownr + 1)) + calcMarginTop();

            int totalPlacesInRow = ALLOWED_BRICKS_IN_ROW[Tools.getRandomBetween(0, 2)];
            int powerupsInRow = powerupsPerRow[rownr];
            int powerdownsInRow = powerdownsPerRow[rownr];
            int emptyPlacesInRow = getNumberOfEmptyPlacesInRow(totalPlacesInRow);
            int normalBricksInRow = totalPlacesInRow - powerupsInRow - powerdownsInRow - emptyPlacesInRow;
            numberOfTotalBricksInField += totalPlacesInRow - emptyPlacesInRow;
            
            brickRows[rownr] = new BrickRow(normalBricksInRow, powerupsInRow, powerdownsInRow, emptyPlacesInRow, y, multiplayer);
        }
    }
    
    private int[] getPowersPerRow(int number){
        int[] array = new int[numberOfRows];
        for (int i = 0; (i < number) && (i < difficulty.getRows()) ; i++){
              array[i] = 1;
        }
        return Tools.shuffleArray(array);
    }

    private int getNumberOfEmptyPlacesInRow(int numberOfTotalPlacesInRow) {
        if (isMultiplayer()) {
            return (int) (numberOfTotalPlacesInRow * 0.45);
        }
        return 0;
    }

    public BrickRow getRow(int rowIndex) {
        return getBrickRows()[rowIndex];
    }

    public Brick getBrick(int rowIndex, int colIndex) {
        return getRow(rowIndex).getBrick(colIndex);
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public boolean isSingleplayer() {
        return !multiplayer;
    }
    
    public int getNumberOfTotalBricksInField() {
        return numberOfTotalBricksInField;
    }
    
    public BrickRow[] getBrickRows() {
        return brickRows;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isMultiplayer() == true ? "Multiplayer" : "Singleplayer").append("\n");
        for (BrickRow field1 : getBrickRows()) {
            sb.append(field1.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
 
}