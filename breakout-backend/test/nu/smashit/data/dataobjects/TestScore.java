package nu.smashit.data.dataobjects;

import nu.smashit.core.bodies.Brick;
import nu.smashit.data.dataobjects.BrickType.Type;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jonas
 */
public class TestScore {

    private Score score;

    public TestScore() {
    }

    @Before
    public void start() {
        score = new Score();
    }

    @Test
    public void testStartScore() {
        assertEquals(score.getPoints(), Score.START_POINTS);
        assertTrue(score.isAlive());
    }

    @Test
    public void testSmashBricks() {
        int points = 5;
        Brick brick = new Brick(0, 0, 0, 0, new BrickType("", Type.N, points, 1));

        score.addBrickSmash(brick);
        assertEquals(score.getPoints(), Score.START_POINTS + points);
        assertTrue(score.isAlive());
    }

    @Test
    public void testDeath() {
        score.subtractDeath();
        assertEquals(score.getPoints(), Score.START_POINTS - Score.DEATH_POINTS);
        
        score.addOpponentDeath();
        assertEquals(score.getPoints(), Score.START_POINTS);
        
        score.subtractDeath();
        assertEquals(score.getPoints(), Score.START_POINTS - Score.DEATH_POINTS);

        score.subtractDeath();
        assertEquals(score.getPoints(), Score.START_POINTS - Score.DEATH_POINTS - Score.DEATH_POINTS);

        assertFalse(score.isAlive());
    }

    @Test
    public void testGame() {
        assertTrue(score.isAlive());

        score.subtractDeath();
        assertTrue(score.isAlive());

        int points = 5;
        Brick brick = new Brick(0, 0, 0, 0, new BrickType("", Type.N, points, 1));
        score.addBrickSmash(brick);
        assertTrue(score.isAlive());        

        score.subtractDeath();
        assertTrue(score.isAlive());
        
        score.subtractDeath();
        assertFalse(score.isAlive());
    }

}