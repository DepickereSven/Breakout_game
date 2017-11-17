package nu.smashit.core.bodies;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class TestMobableBody {
    
    public TestMobableBody() {}
    
    @Test
    public void moveMovableBody() {
        int x = 5;
        int y = 5;
        int width = 10;
        int height = 2;
        int dx = 3;
        int dy = 3;
        
        MovableBody mb = new MovableBody(x,y,width, height);
        mb.move(dx, dy);
        assertEquals(mb.getX(), x + dx);
        assertEquals(mb.getY(), x + dy);
        assertEquals(mb.getWidth(), width);
        assertEquals(mb.getHeight(), height);
    }
    
}
