package nu.smashit.core.bodies;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class TestBall {

    public TestBall() {
    }

    @Test
    public void testBallInverseHorSpeed() {
        Ball ball = new Ball();
        assertTrue(ball.isGoingUp());
        assertTrue(ball.isGoingLeft());

        ball.inverseHozSpeed();
        assertTrue(ball.isGoingUp());
        assertTrue(ball.isGoingRight());

        ball.inverseHozSpeed();
        assertTrue(ball.isGoingUp());
        assertTrue(ball.isGoingLeft());
    }

    @Test
    public void testBallInverseVerSpeed() {
        Ball ball = new Ball();
        assertTrue(ball.isGoingUp());
        assertTrue(ball.isGoingLeft());

        ball.inverseVerSpeed();
        assertTrue(ball.isGoingDown());
        assertTrue(ball.isGoingLeft());

        ball.inverseVerSpeed();
        assertTrue(ball.isGoingUp());
        assertTrue(ball.isGoingLeft());
    }

    @Test
    public void testMoveBall() {
        Ball ball = new Ball();

        ball.move();
        int expectedNewX = Ball.X_START_POS + (int) (ball.getDxStartValue() * Ball.MULTIPLIER);
        int expectedNewY = Ball.Y_START_POS + (int) (ball.getDyStartValue() * Ball.MULTIPLIER);
        assertEquals(ball.getX(), expectedNewX);
        assertEquals(ball.getY(), expectedNewY);

        ball.move();
        expectedNewX = expectedNewX + (int) (ball.getDxStartValue() * Ball.MULTIPLIER);
        expectedNewY = expectedNewY + (int) (ball.getDyStartValue() * Ball.MULTIPLIER);
        assertEquals(ball.getX(), expectedNewX);
        assertEquals(ball.getY(), expectedNewY);
    }

    @Test
    public void testMoveBallAndInverseSpeed() {
        Ball ball = new Ball();

        ball.move();
        int expectedNewX = Ball.X_START_POS + (int) (ball.getDxStartValue() * Ball.MULTIPLIER);
        int expectedNewY = Ball.Y_START_POS + (int) (ball.getDyStartValue() * Ball.MULTIPLIER);
        assertEquals(ball.getX(), expectedNewX);
        assertEquals(ball.getY(), expectedNewY);
        assertTrue(ball.isGoingLeft());
        assertTrue(ball.isGoingUp());

        ball.inverseHozSpeed();
        ball.move();
        expectedNewX = expectedNewX - (int) (ball.getDxStartValue() * Ball.MULTIPLIER);
        expectedNewY = expectedNewY + (int) (ball.getDyStartValue() * Ball.MULTIPLIER);
        assertEquals(ball.getX(), expectedNewX);
        assertEquals(ball.getY(), expectedNewY);
        assertTrue(ball.isGoingRight());
        assertTrue(ball.isGoingUp());

        ball.inverseVerSpeed();
        ball.move();
        expectedNewX = expectedNewX - (int) (ball.getDxStartValue() * Ball.MULTIPLIER);
        expectedNewY = expectedNewY - (int) (ball.getDyStartValue() * Ball.MULTIPLIER);
        assertEquals(ball.getX(), expectedNewX);
        assertEquals(ball.getY(), expectedNewY);
        assertTrue(ball.isGoingRight());
        assertTrue(ball.isGoingDown());
    }
}
