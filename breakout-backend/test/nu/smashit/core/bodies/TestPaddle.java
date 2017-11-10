package nu.smashit.core.bodies;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Jonas
 */
public class TestPaddle {
    
    int x;
    int y;
    Paddle paddle;
    
    public TestPaddle() {}
    
    @Before
    public void setUp(){
        x = Paddle.X_START_POS;
        y = 5;
        paddle = new Paddle(y);
    }
    
    @After
    public void testIfYStaysTheSame(){
        assertEquals(y, paddle.y);
    }
    
    @Test
    public void goLeftAndRight() {
        paddle.goLeft();
        paddle.goRight();
        paddle.move();
        assertEquals(x, paddle.x);
    }
     
     @Test
     public void goRight() {
        paddle.goRight();
        paddle.move();
        assertEquals(x+ Paddle.MOVE_STEP_SIZE, paddle.x);
     }
     
    @Test
    public void goLeft() {
        paddle.goLeft();
        paddle.move();
        assertEquals(x - Paddle.MOVE_STEP_SIZE, paddle.x);
    }
     
    @Test
    public void goRightTwice() {
        paddle.goRight();
        paddle.goRight();
        paddle.move();
        assertEquals(x + (Paddle.MOVE_STEP_SIZE*2), paddle.x);
    }
     
    @Test
    public void goLeftTwice() {
        paddle.goLeft();
        paddle.goLeft();
        paddle.move();
        assertEquals(x - (Paddle.MOVE_STEP_SIZE*2), paddle.x);
    }
     
    @Test
    public void goNoWhere(){
        paddle.goNowhere();
        paddle.move();
        assertEquals(x, paddle.x);
    }
     
}
