package org.helsinki.back.mapmaker;

import java.util.ArrayList;

public class Coordinate {
    private int x;
    private int y;
    private ArrayList<Vertex> vertices;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.vertices = new ArrayList<>();
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

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public String toString() {
        return this.x + ", " + this.y;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
