package nu.smashit.core.bodies;

import com.fasterxml.jackson.annotation.JsonProperty;
import nu.smashit.core.GameCanvas;

/**
 *
 * @author jodus
 */
public class Body {

    public int x;
    public int y;
    @JsonProperty("w")
    public int width;
    @JsonProperty("h")
    public int height;

    public Body(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void reverseY() {
        this.y = GameCanvas.HEIGHT - y - height;
    }
}
