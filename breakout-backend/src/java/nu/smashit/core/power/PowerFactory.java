package nu.smashit.core.power;

// @author Jonas

import nu.smashit.data.dataobjects.BrickType;

public class PowerFactory {

    public Power createPower(BrickType brickType){
        String subType = brickType.getSubType();
        if (subType == null || brickType.getType() == BrickType.BrickSort.N){
            return new NoPower();
        }else{
            int value = brickType.getValue();
            switch (subType){
                case "POINTS":
                    return new Points(value);
                case "BALL_DIAMETER":
                    return new BallDiameter(value);
                case "PALET_SIZE":
                    return new PaletSize(value);
                case "START_SPEED":
                    return new StartSpeed(value);
                default:
                    return new NoPower();
            }
        }
    }
    
}
