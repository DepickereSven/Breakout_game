package nu.smashit.data;

// @author Jonas

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nu.smashit.data.dataobjects.BrickType;
import nu.smashit.data.utils.MySqlConnection;
import nu.smashit.utils.BreakoutException;

public class MySqlBrickTypeRepository implements BrickTypeRepository{
    
    private static final String SQL_GET_ALL_BRICKTYPES = "SELECT * FROM brick_type";
    private static final String SQL_GET_BRICKTYPE =  SQL_GET_ALL_BRICKTYPES + "WHERE name = ?";
    private static final String SQL_GET_ALL_BRICKTYPES_OF_TYPE = SQL_GET_ALL_BRICKTYPES + "WHERE type = ?";
    
    @Override
    public BrickType getBrickType(String name) {
       try(    Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_BRICKTYPE);    ){
           
            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            return createBrickTypeFromResultSet(rs);
            
        }catch(SQLException ex){
            throw new BreakoutException("Could not get brick type.", ex);
        }
    }

    @Override
    public List<BrickType> getAllBricksOfType(String type) {
        try(    Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_ALL_BRICKTYPES_OF_TYPE);    ){
            
            prep.setString(1, type);
            ResultSet rs = prep.executeQuery();
            
            List<BrickType> types = new ArrayList();
            while(rs.next()){
                types.add(createBrickTypeFromResultSet(rs));
            }
            return types;
            
        }catch(SQLException ex){
            throw new BreakoutException("Could not get brick types.", ex);
        }
    }

    @Override
    public List<BrickType> getAllBricks() {
        try(    Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_ALL_BRICKTYPES);    ){
            
            ResultSet rs = prep.executeQuery();
            
            List<BrickType> types = new ArrayList();
            while(rs.next()){
                types.add(createBrickTypeFromResultSet(rs));
            }
            return types;
            
        }catch(SQLException ex){
            throw new BreakoutException("Could not get all brick types.", ex);
        }
    }
    
    private BrickType createBrickTypeFromResultSet(ResultSet rs){
        try{      
            int brickID = rs.getInt("brickID");
            BrickType.BrickSort sort = BrickType.BrickSort.valueOf(rs.getString("type"));
            int brickStrength = rs.getInt("brick_strength");
            int points = rs.getInt("points");
            int value = rs.getInt("value");
            String name = rs.getString("name");
            
            return new BrickType(brickID, name, sort, brickStrength, points, value);
        }catch(SQLException ex){
            throw new BreakoutException("Could not create brick type.", ex);
        }
    }

}
