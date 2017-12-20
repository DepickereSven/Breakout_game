package nu.smashit.core.bodies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import nu.smashit.core.GameCanvas;
import nu.smashit.data.Repositories;
import nu.smashit.data.dataobjects.BrickType;
import nu.smashit.utils.Tools;

/**
 *
 * @author jodus
 */
public class BrickRow extends Body {

    private final Brick[] bricks;
    private final int numberOfNormalBricks;
    private final int numberOfPowerups;
    private final int numberOfPowerdowns;
    private final int numberOfEmptyPlaces;
    private static final Random RANDOM = new Random();
    private Map<BrickType.Type, List<BrickType>> cachedBrickTypes;
    private final boolean multiplayer;
    
    public BrickRow(int numberOfNormalBricks, int numberOfPowerups, int numberOfPowerdowns, int numberOfEmptyPlaces, int y, boolean multiplayer) {
        super(0, y, GameCanvas.WIDTH, Brick.HEIGHT);

        this.numberOfNormalBricks = numberOfNormalBricks;
        this.numberOfPowerups = numberOfPowerups;
        this.numberOfPowerdowns = numberOfPowerdowns;
        this.numberOfEmptyPlaces = numberOfEmptyPlaces;
        this.multiplayer = multiplayer;

        bricks = new Brick[getNumberOfTotalPlaces()];
        cachedBrickTypes = new HashMap<>();
        fillRow();
    }

    public Brick getBrick(int colIndex) {
        return getBricks()[colIndex];
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
    
    public Brick[] getBricks() {
        return bricks;
    }

    private void fillRow() {
        fillType(numberOfNormalBricks, BrickType.Type.N);
        fillType(numberOfPowerups, BrickType.Type.U);
        fillType(numberOfPowerdowns, BrickType.Type.D);
    }

    private void fillType(int number, BrickType.Type sort) {
        int block_width = (int) (GameCanvas.WIDTH / getNumberOfTotalPlaces());
        int place;

        for (int bricknr = 0; bricknr < number; bricknr++) {
            do {
                place = Tools.getRandomBetween(0, getNumberOfTotalPlaces() - 1);
            } while (getBricks()[place] != null);

            int x = place * block_width;
            bricks[place] = new Brick(x, getY(), block_width, Brick.HEIGHT, getRandomBrickType(sort));
        }
    }

    private BrickType getRandomBrickType(BrickType.Type sort) {
        if (!cachedBrickTypes.containsKey(sort)){
             cachedBrickTypes.put(sort, Repositories.getBrickTypeRepository().getAllBricksOfType( sort.toString()));
        }
        
        BrickType brickType;
        do{
            int i = Tools.getRandomBetween(0, cachedBrickTypes.get(sort).size()-1);
            brickType = cachedBrickTypes.get(sort).get(i);
        }while (isSinglePlayer()
                && brickType.getType() != BrickType.Type.N 
                && brickType.getPowerData().getType().equals("POINTS"));
        
        return brickType;
    }
    
    private boolean isSinglePlayer(){
        return !multiplayer;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Brick row1 : getBricks()) {
            if (row1 == null) {
                sb.append("*.............*");
            } else {
                sb.append(row1.toString());
            }
        }
        return sb.toString();
    }

}
