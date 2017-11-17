package nu.smashit.core;

import nu.smashit.core.bodies.Ball;
import nu.smashit.core.bodies.Body;

/**
 *
 * @author jodus
 */
public class Collision {

    private static double getBallX(Ball ball) {
        return ball.getDx() * 0.7 + ball.x;
    }

    private static double getBallY(Ball ball) {
        return ball.getDy() * 0.7 + ball.y;
    }

    public static boolean isWallCollision(Ball b) {
        return b.isGoingLeft() && b.x < 0
                || !b.isGoingLeft() && b.x > GameCanvas.WIDTH - b.width;
    }

    public static boolean isCeilingCollision(Ball b) {
        return b.isGoingUp() && b.y < 0;
    }

    public static boolean isFloorCollision(Ball b) {
        return !b.isGoingUp() && b.y > GameCanvas.HEIGHT - b.height;
    }

    public static boolean isCollision(Ball ball, Body body) {
        return isHozCollision(ball, body) && isVerCollision(ball, body);
    }

    public static boolean isVerCollision(Ball ball, Body body) {
        double ballY = getBallY(ball);
        return ballY <= body.y + body.height
                && ballY + ball.height >= body.y;
    }

    public static boolean isHozCollision(Ball ball, Body body) {
        double ballX = getBallX(ball);
        return ballX <= body.x + body.width
                && ballX + ball.width >= body.x;
    }

    public static boolean isTopOrBottomCollision(Ball ball, Body body) {
        return ball.y <= body.y - (body.height / 2)
                || ball.y >= body.y + (body.height / 2);
    }
}
