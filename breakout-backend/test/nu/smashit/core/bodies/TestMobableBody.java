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
        assertEquals(mb.x, x + dx);
        assertEquals(mb.y, x + dy);
        assertEquals(mb.width, width);
        assertEquals(mb.height, height);
    }
    
}
