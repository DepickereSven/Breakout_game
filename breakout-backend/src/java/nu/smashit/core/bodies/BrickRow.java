package nu.smashit.core.bodies;

import nu.smashit.core.GameCanvas;
import nu.smashit.data.dataobjects.BrickType;
import nu.smashit.utils.Tools;

/**
 *
 * @author jodus
 */
public class BrickRow extends Body {

    public final Brick[] bricks;
    private final int numberOfNormalBricks;
    private final int numberOfPowerups;
    private final int numberOfPowerdowns;
    private final int numberOfEmptyPlaces;

    public BrickRow(int numberOfNormalBricks, int numberOfPowerups, int numberOfPowerdowns, int numberOfEmptyPlaces, int y) {
        super(0, y, GameCanvas.WIDTH, Brick.HEIGHT);

        this.numberOfNormalBricks = numberOfNormalBricks;
        this.numberOfPowerups = numberOfPowerups;
        this.numberOfPowerdowns = numberOfPowerdowns;
        this.numberOfEmptyPlaces = numberOfEmptyPlaces;

        bricks = new Brick[getNumberOfTotalPlaces()];
        fillRow();
    }

    public Brick getBrick(int colIndex) {
        return bricks[colIndex];
    }

    public int getNumberOfTotalPlaces() {
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

    public int getNumberOfEmptyPlaces() {
        return numberOfEmptyPlaces;
    }

    private void fillRow() {
        fillType(numberOfNormalBricks, BrickType.BrickSort.N);
        fillType(numberOfPowerups, BrickType.BrickSort.U);
        fillType(numberOfPowerdowns, BrickType.BrickSort.D);
    }

    private void fillType(int number, BrickType.BrickSort sort) {
        int block_width = (int) (GameCanvas.WIDTH / getNumberOfTotalPlaces());
        int place;

        for (int bricknr = 0; bricknr < number; bricknr++) {
            do {
                place = Tools.getRandomBetween(0, getNumberOfTotalPlaces() - 1);
            } while (bricks[place] != null);

            int x = place * block_width;
            bricks[place] = new Brick(x + y, x, y, block_width, Brick.HEIGHT, getRandomBrickType(sort));
        }
    }

    private BrickType getRandomBrickType(BrickType.BrickSort sort) {
        //TODO uit db halen
        return new BrickType("test", sort);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Brick row1 : bricks) {
            if (row1 == null) {
                sb.append("*.............*");
            } else {
                sb.append(row1.toString());
            }
        }
        return sb.toString();
    }
}
