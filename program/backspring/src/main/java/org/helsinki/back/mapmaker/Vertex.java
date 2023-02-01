package org.helsinki.back.mapmaker;

import java.util.List;

public class Vertex {
    int x;
    int y;
    List<Coordinate> closest;

    public Vertex(int x, int y, List<Coordinate> closest) {
        this.x = x;
        this.y = y;
        this.closest = closest;
    }

    public String getCoordinatesString() {
        return Integer.toString(x) + ", " + Integer.toString(y);
    }

    public String getXString() {
        return Integer.toString(x);
    }

    public String getYString() {
        return Integer.toString(y);
    }
}
