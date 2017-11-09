package nu.smashit.core;

import org.junit.Test;

public class TestField {
    
    public TestField() {}
    
    @Test
    public void testFieldMultiplayer(){
        Field field = Field.getMultiplayerInstance();
        System.out.println(field.toString());
    }
    
    @Test
    public void testFieldSingleplayer(){
        Field field = Field.getSingleplayerInstance(2);
        System.out.println(field.toString());
    }
}
