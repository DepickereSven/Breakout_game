/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

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

    Body(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isCollision(Body b) {
        return x < b.x + b.width
                && x + width > b.x
                && y < b.y + b.height
                && height + y > b.y;
    }
}
