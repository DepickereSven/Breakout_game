package nu.smashit.data;

// @author Jonas

import java.util.List;
import nu.smashit.data.dataobjects.BrickType;

public class MySqlBrickTypeRepository implements BrickTypeRepository{

    @Override
    public BrickType getBrickType(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BrickType> getAllBricksOfType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<BrickType> getAllBricks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
