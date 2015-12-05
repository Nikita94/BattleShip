package com.rmi;

import java.io.Serializable;

/**
 * Created by nikita on 04.12.2015.
 */

public class PositionDeck implements Serializable {
    private int x;
    private int y;

    public PositionDeck(int x, int y) {this.x = x; this.y = y;}
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}