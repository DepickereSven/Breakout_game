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
public class Paddle extends MovableBody {

    public Paddle() {
        super(0, GameCanvas.HEIGHT - 20, 70, 20);
    }
}
