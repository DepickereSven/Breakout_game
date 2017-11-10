package nu.smashit.data;

// @author Jonas

import java.util.List;
import nu.smashit.data.dataobjects.BrickType;

public interface BrickTypeRepository {

    public BrickType getBrickType(String name);
    public List<BrickType> getAllBricksOfType(String type);
    public List<BrickType> getAllBricks();
    
}