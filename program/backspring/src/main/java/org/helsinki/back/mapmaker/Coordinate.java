package org.helsinki.back.mapmaker;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String getX() {
        return Integer.toString(x);
    }

    public String getY() {
        return Integer.toString(y);
    }
}
