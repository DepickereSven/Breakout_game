package nu.smashit.core;

// @author Jonas
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

public class Brick extends Body {

    private final BrickType type;
    private int lives;

    public final UUID id;

    public static final int HEIGHT = 16;

    public Brick(int x, int y, int width, int height, BrickType type) {
        super(x, y, width, height);
        this.id = UUID.randomUUID();
        this.type = type;
        this.lives = type.getBrickStrength();
    }

    public int getLives() {
        return lives;
    }

    public void smashBrick() {
        lives--;
    }
    
    public BrickType getBrickType(){
        return type;
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
