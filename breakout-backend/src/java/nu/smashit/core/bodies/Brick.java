package nu.smashit.core.bodies;

// @author Jonas
import com.fasterxml.jackson.annotation.JsonFormat;
import nu.smashit.data.dataobjects.BrickType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@JsonIgnoreProperties(value = {"h"})
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Brick extends Body {

    @JsonProperty("t")
    @JsonSerialize(using = ToStringSerializer.class)
    public final BrickType type;
    private int lives;

    public static final int HEIGHT = 16;

    public Brick(int x, int y, int width, int height, BrickType type) {
        super(x, y, width, height);
        this.type = type;
        this.lives = type.getBrickStrength();
    }

    @JsonProperty("l")
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
