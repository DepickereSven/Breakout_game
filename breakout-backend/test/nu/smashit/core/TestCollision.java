package nu.smashit.core;

import nu.smashit.core.bodies.Ball;
import nu.smashit.core.bodies.Body;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class TestCollision {

    private static final int LEFT = -1;
    private static final int RIGHT = GameCanvas.WIDTH + Ball.WIDTH;
    private static final int BOTTOM = GameCanvas.HEIGHT + Ball.HEIGHT;
    private static final int TOP = -1;
    private static final int MIDDLE = 50;
    
    public TestCollision() {}
    
    private Ball getBall(int x, int y, boolean left, boolean up){
        Ball b = Mockito.mock(Ball.class);
        when(b.getX()).thenReturn(x);
        when(b.getY()).thenReturn(y);
        when(b.isGoingLeft()).thenReturn(left);
        when(b.isGoingUp()).thenReturn(up);
        when(b.isGoingRight()).thenReturn(!left);
        when(b.isGoingDown()).thenReturn(!up);
        return b;
    }

    @Test
    public void testWallCollision() {
        Ball b1 = getBall(LEFT, MIDDLE, true, true);
        assertTrue( Collision.isWallCollision(b1) );
        
        Ball b2 = getBall(RIGHT, MIDDLE, false, true);
        assertTrue( Collision.isWallCollision(b2) );
        
        Ball b3 = getBall(MIDDLE, MIDDLE, true, true);
        assertFalse( Collision.isWallCollision(b3) );
    }

    @Test
    public void testCeilingCollision() {
        Ball b1 = getBall(MIDDLE, TOP, true, true);
        assertTrue( Collision.isCeilingCollision(b1) );
        
        Ball b2 = getBall(MIDDLE, BOTTOM, true, false);
        assertFalse( Collision.isCeilingCollision(b2) );
        
        Ball b3 = getBall(MIDDLE, MIDDLE, true, false);
        assertFalse( Collision.isCeilingCollision(b3) );
    }

    @Test
    public void testFloorCollision() {
        Ball b1 = getBall(MIDDLE, TOP, true, true);
        assertFalse( Collision.isFloorCollision(b1) );
        
        Ball b2 = getBall(MIDDLE, BOTTOM, true, false);
        assertTrue( Collision.isFloorCollision(b2) );
        
        Ball b3 = getBall(MIDDLE, MIDDLE, true, false);
        assertFalse( Collision.isFloorCollision(b3) );
    }

    @Test
    public void testVerCollision() {
        Ball b1 = getBall(MIDDLE, MIDDLE, true, true);
        Body body = new Body(MIDDLE - b1.getHeight(), MIDDLE, 10, 10);
        assertTrue( Collision.isVerCollision(b1, body) );
        
        Ball b2 = getBall(MIDDLE, MIDDLE, true, true);
        Body body2 = new Body(LEFT, TOP, 10, 10);
        assertFalse( Collision.isVerCollision(b2, body2) );
    }

    @Test
    public void testHozCollision() {
        Ball b1 = getBall(MIDDLE, MIDDLE, true, true);
        Body body = new Body(MIDDLE, MIDDLE - b1.getWidth(), 10, 10);
        assertTrue( Collision.isVerCollision(b1, body) );
        
        Ball b2 = getBall(MIDDLE, MIDDLE, true, true);
        Body body2 = new Body(LEFT, TOP, 10, 10);
        assertFalse( Collision.isVerCollision(b2, body2) );
    }

}