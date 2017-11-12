package nu.smashit.core.bodies;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import nu.smashit.core.GameCanvas;

/**
 *
 * @author jodus
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public class Body {

    public int x;
    public int y;
    public int width;
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
