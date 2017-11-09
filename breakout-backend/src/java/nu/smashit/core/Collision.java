/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

/**
 *
 * @author jodus
 */
public class Collision {
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
        double slop = 0.7;
        double ballX = ball.dx * slop + ball.x;
        double ballY = ball.dy * slop + ball.y;
        return ballX < body.x + body.width
                && ballX + ball.width > body.x
                && ballY < body.y + body.height
                && ball.height + ballY > body.y;
    }

    public static boolean isVerCollision(Ball ball, Body body) {
        return ball.y <= body.y - (body.height / 2)
                || ball.y >= body.y + (body.height / 2);
    }
}
