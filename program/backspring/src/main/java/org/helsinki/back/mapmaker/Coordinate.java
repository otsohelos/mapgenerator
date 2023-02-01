package org.helsinki.back.mapmaker;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getXString() {
        return Integer.toString(x);
    }

    public String getYString() {
        return Integer.toString(y);
    }
}
