package nu.smashit.core.bodies;

// @author Jonas
import nu.smashit.data.dataobjects.BrickType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Brick extends Body {

    @JsonSerialize(using = ToStringSerializer.class)
    public final BrickType type;
    private int lives;

    public final int id;

    public static final int HEIGHT = 16;

    public Brick(int id, int x, int y, int width, int height, BrickType type) {
        super(x, y, width, height);
        this.id = id;
        this.type = type;
        this.lives = type.getBrickStrength();
    }

    public int getLives() {
        return lives;
    }

    public void smashBrick() {
        lives--;
    }

    @JsonIgnore
    public boolean isBroken() {
        return lives < 1;
    }

    @Override
    public String toString() {
        return "Brick" + type + "(" + lives + ")";
    }

}
