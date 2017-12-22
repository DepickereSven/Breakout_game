package nu.smashit.core.power;

// @author Jonas
import nu.smashit.data.dataobjects.BrickType;
import nu.smashit.data.dataobjects.PowerData;

public class PowerFactory {

    public Power createPower(BrickType brickType) {
        PowerData powerData = brickType.getPowerData();

        if (powerData == null
                && brickType.getType() != BrickType.Type.U
                && brickType.getType() != BrickType.Type.D) {
            return new NoPower();
        } else {
            int value = powerData.getValue();
            int powerID = powerData.getPowerID();
            switch (powerData.getType()) {
                case "POINTS":
                    return new Points(powerID, value);
                case "BALL_DIAMETER":
                    return new BallDiameter(powerID, value);
                case "PALET_SIZE":
                    return new PaletSize(powerID, value);
                case "START_SPEED":
                    return new StartSpeed(powerID, value);
                default:
                    return new NoPower();
            }
        }
    }

}
