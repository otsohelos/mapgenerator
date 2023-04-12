package org.helsinki.back.mapmaker;

import java.util.ArrayList;

public class Coordinate {
    private double x;
    private double y;
    private ArrayList<Vertex> vertices;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
        this.vertices = new ArrayList<>();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getXString() {
        return Double.toString(x*1000);
    }

    public String getYString() {
        return Double.toString(y*1000);
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
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
