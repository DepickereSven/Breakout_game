package nu.smashit.core;

import nu.smashit.core.bodies.Ball;
import nu.smashit.core.bodies.Body;

/**
 *
 * @author jodus
 */
public class Collision {

    private static double getBallX(Ball ball) {
        return ball.getDx() * 0.7 + ball.getX();
    }

    private static double getBallY(Ball ball) {
        return ball.getDy() * 0.7 + ball.getY();
    }

    public static boolean isWallCollision(Ball b) {
        return b.isGoingLeft() && b.getX() < 0
                || b.isGoingRight() && b.getX() > GameCanvas.WIDTH - b.getWidth();
    }

    public static boolean isCeilingCollision(Ball b) {
        return b.isGoingUp() && b.getY() < 0;
    }

    public static boolean isFloorCollision(Ball b) {
        return b.isGoingDown() && b.getY() > GameCanvas.HEIGHT - b.getHeight();
    }

    public static boolean isCollision(Ball ball, Body body) {
        return isHorCollision(ball, body) && isVerCollision(ball, body);
    }

    public static boolean isVerCollision(Ball ball, Body body) {
        double ballY = getBallY(ball);
        return ballY <= body.getY() + body.getHeight()
                && ballY + ball.getHeight() >= body.getY();
    }

    public static boolean isHorCollision(Ball ball, Body body) {
        double ballX = getBallX(ball);
        return ballX <= body.getX() + body.getWidth()
                && ballX + ball.getWidth() >= body.getX();
    }

    public static boolean isTopOrBottomCollision(Ball ball, Body body) {
        return ball.getY() <= body.getY() - (body.getHeight() / 2)
                || ball.getY() >= body.getY() + (body.getHeight() / 2);
    }
}
