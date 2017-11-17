package nu.smashit.core.bodies;

import nu.smashit.data.Repositories;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestField {
    
    public TestField() {}
    
    Field field;
    
    @After
    public void after(){
        assertEquals(field.getRow(0).getBrick(0), field.getBrick(0, 0));
        System.out.println(field.toString());
    }
    
    @Test
    public void testFieldMultiplayer(){
        field = Field.getMultiplayerInstance();
    }
    
    @Test
    public void testFieldSingleplayer(){
        field = Field.getSingleplayerInstance(Repositories.getLevelRepository().getDifficulty(2));
    }
}
