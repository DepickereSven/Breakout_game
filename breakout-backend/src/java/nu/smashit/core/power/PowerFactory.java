package nu.smashit.core.power;

// @author Jonas

import nu.smashit.data.dataobjects.BrickType;
import nu.smashit.data.dataobjects.PowerData;

public class PowerFactory {

    public Power createPower(BrickType brickType){
        PowerData powerData = brickType.getPowerData();
        if (powerData == null 
                || brickType.getType() != BrickType.Type.U 
                || brickType.getType() != BrickType.Type.D ){
            return new NoPower();
        }else{
            int value = powerData.getValue();
            switch (powerData.getType()){
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
