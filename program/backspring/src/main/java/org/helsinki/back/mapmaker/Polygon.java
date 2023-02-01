package org.helsinki.back.mapmaker;

public class Polygon {
    private int numberOfPoints;
    private int[] xs;
    private int[] ys;

    public Polygon(int[] xs, int[] ys, int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
        this.xs = xs;
        this.ys = ys;
    }

    public int[] getXs() {
        return xs;
    }

    public int[] getYs() {
        return ys;
    }

}
