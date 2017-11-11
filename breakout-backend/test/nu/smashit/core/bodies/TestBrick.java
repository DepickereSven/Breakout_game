package nu.smashit.core.bodies;

import nu.smashit.data.dataobjects.BrickType;
import nu.smashit.data.dataobjects.BrickType.BrickSort;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class TestBrick {

    public TestBrick() {}

    @Test
    public void testBrickWith0Lives() {
        int lives = 0;
        Brick brick = createNormalBrick(lives);
        assertTrue(brick.isBroken());
        assertEquals(BrickSort.N, brick.getBrickType().getSort());
        assertEquals(brick.getLives(), lives);

        brick.smashBrick();
        assertTrue(brick.isBroken());
    }

    @Test
    public void testBrickWith1Live() {
        int lives = 1;
        Brick brick = createNormalBrick(lives);
        assertFalse(brick.isBroken());
        assertEquals(BrickSort.N, brick.getBrickType().getSort());
        assertEquals(brick.getLives(), lives);

        brick.smashBrick();
        assertTrue(brick.isBroken());
        assertEquals(brick.getLives(), lives - 1);

        brick.smashBrick();
        assertTrue(brick.isBroken());
    }

    @Test
    public void testBrickWith3Lives() {
        int lives = 3;
        Brick brick = createNormalBrick(lives);
        assertFalse(brick.isBroken());
        assertEquals(BrickSort.N, brick.getBrickType().getSort());
        assertEquals(brick.getLives(), lives);

        brick.smashBrick();
        assertFalse(brick.isBroken());
        assertEquals(brick.getLives(), lives - 1);

        brick.smashBrick();
        assertFalse(brick.isBroken());
        assertEquals(brick.getLives(), lives - 2);

        brick.smashBrick();
        assertTrue(brick.isBroken());
        assertEquals(brick.getLives(), lives - 3);

        brick.smashBrick();
        assertTrue(brick.isBroken());
    }

    private Brick createNormalBrick(int lives) {
        return new Brick(10, 10, 10, 10, new BrickType("TESTBRICK", BrickSort.N, lives, 10));
    }
}
