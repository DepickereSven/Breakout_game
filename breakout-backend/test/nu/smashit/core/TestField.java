package nu.smashit.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jonas
 */
public class TestField {
    
    public TestField() {}
    
    @Test
    public void testFieldMultiplayer(){
        Field field = new Field(true);
        System.out.println(field.toString());
    }
    
    @Test
    public void testFieldSingleplayer(){
        Field field = new Field(false, 2);
        System.out.println(field.toString());
    }
}
