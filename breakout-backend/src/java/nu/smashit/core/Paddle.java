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

    public Paddle(int y) {
        super(0, y, 50, 14);
    }
}
