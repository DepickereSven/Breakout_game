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
public class MovableBody extends Body {

    public MovableBody(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}
