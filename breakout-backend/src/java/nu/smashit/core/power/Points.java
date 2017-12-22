package nu.smashit.core.power;

// @author Jonas
import nu.smashit.core.GameLoop;

public class Points extends Power {

    private double pointsFactor;

    public Points(int powerID, double pointsFactor) {
        super(powerID, false);
        this.pointsFactor = pointsFactor;
    }

    @Override
    protected void doEffect(GameLoop gameLoop) {
        if (!getPlayers().isEmpty()) {
            getPlayer().getScore().setPointsFactor(pointsFactor);
        }
    }

    @Override
    protected void undoEffect(GameLoop gameLoop) {
        if (!getPlayers().isEmpty()) {
            getPlayer().getScore().setPointsFactor(1);
        }
    }

}
