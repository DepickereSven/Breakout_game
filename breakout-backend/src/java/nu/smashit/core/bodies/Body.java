package nu.smashit.core.bodies;

import com.fasterxml.jackson.annotation.JsonProperty;
import nu.smashit.core.GameCanvas;

/**
 *
 * @author jodus
 */
public class Body {

    private int x;
    private int y;
    private int width;
    private int height;

    public Body(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void reverseY() {
        this.setY(GameCanvas.HEIGHT - getY() - getHeight());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @JsonProperty("w")
    public int getWidth() {
        return width;
    }

    @JsonProperty("h")
    public int getHeight() {
        return height;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
}
