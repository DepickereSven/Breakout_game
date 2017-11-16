package nu.smashit.core.bodies;

// @author Jonas
import nu.smashit.core.GameCanvas;
import nu.smashit.utils.Tools;

public class Field extends Body {

    public final BrickRow[] brickRows;
    private final int numberOfRows;
    private final int level;
    private final boolean multiplayer;
    private int numberOfNormalBricks;

    private static final int SINGLEPLAYER_MARGIN_TOP = 50;
    private static final int[] ALLOWED_BRICKS_IN_ROW = new int[]{4, 5, 8};

    public static Field getSingleplayerInstance(int level) {
        return new Field(false, level);
    }

    public static Field getMultiplayerInstance() {
        return new Field(true, 1);
    }

    private Field(boolean multiplayer, int level) {
        super(0, 0, GameCanvas.WIDTH, 0);

        this.multiplayer = multiplayer;
        this.level = Tools.validateBetween(level, 1, 100, 1);
        this.numberOfRows = calcNumberOfRows();
        this.brickRows = new BrickRow[numberOfRows];
        numberOfNormalBricks = 0;

        this.height = calcFieldHeight();
        this.y = calcMarginTop() + this.height;

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

        int[] powerupsPerRow = new int[numberOfRows];
        powerupsPerRow[0] = 1;
        powerupsPerRow[1] = 1;
        powerupsPerRow[2] = 1;
        powerupsPerRow = Tools.shuffleArray(powerupsPerRow);

        int[] powerdownsPerRow = Tools.shuffleArray(powerupsPerRow.clone());

        for (int rownr = 0; rownr < numberOfRows; rownr++) {
            int y = (Brick.HEIGHT * (rownr + 1)) + calcMarginTop();

            int totalPlacesInRow = ALLOWED_BRICKS_IN_ROW[Tools.getRandomBetween(0, 2)];
            int powerupsInRow = powerupsPerRow[rownr];
            int powerdownsInRow = powerdownsPerRow[rownr];
            int emptyPlacesInRow = getNumberOfEmptyPlacesInRow(totalPlacesInRow);
            int normalBricksInRow = totalPlacesInRow - powerupsInRow - powerdownsInRow - emptyPlacesInRow;

            numberOfNormalBricks += totalPlacesInRow - emptyPlacesInRow;

            brickRows[rownr] = new BrickRow(normalBricksInRow, powerupsInRow, powerdownsInRow, emptyPlacesInRow, y);
        }
    }

    private int calcNumberOfRows() {
        //TODO read parameters from database
        if (isMultiplayer()) {
            return 6;
        }

        if (isSingleplayer()) {
            if (level <= 2) {
                return 3;
            } else if (level > 2 && level <= 4) {
                return 4;
            } else if (level > 4 && level <= 10) {
                return 5;
            } else if (level > 10 && level <= 40) {
                return 6;
            } else if (level > 40 && level <= 75) {
                return 7;
            } else {
                return 8;
            }
        }

        throw new Error("Error in field");
    }

    private int getNumberOfEmptyPlacesInRow(int numberOfTotalPlacesInRow) {
        if (isMultiplayer()) {
            return (int) (numberOfTotalPlacesInRow * 0.45);
        }
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isMultiplayer() == true ? "Multiplayer" : "Singleplayer").append("\n");
        for (BrickRow field1 : brickRows) {
            sb.append(field1.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public BrickRow getRow(int rowIndex) {
        return brickRows[rowIndex];
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

    public int getNumberOfNormalBricks() {
        return numberOfNormalBricks;
    }
}
