package nu.smashit.data;

// @author Jonas

import java.util.List;
import nu.smashit.core.BrickType;

public interface BrickTypeRepository {

    public BrickType getBrickType(String name);
    public List<BrickType> getAllBricksOfType(String type);
    public List<BrickType> getAllBricks();
    
}