package org.helsinki.back.mapmaker;

public class Edge {
    private Coordinate start;
    private Coordinate left;
    private Coordinate right;
    private Coordinate end;
    private Edge neighbor;
    private double slope;
    private Coordinate midpoint;
    private double yIntercept;

    public Edge(Coordinate start, Coordinate left, Coordinate right) {
        this.start = start;
        this.left = left;
        this.right = right;
        this.slope = 1.0 * (right.getX() - left.getX()) / (left.getY() - right.getY());
        this.midpoint = new Coordinate((right.getX() + left.getX()) / 2, (left.getY() + right.getY()) / 2);
        this.yIntercept = midpoint.getY() - slope * midpoint.getX();

    }

    public void setNeighbor(Edge edge) {
        this.neighbor = edge;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return this.yIntercept;
    }

    public void setEnd(Coordinate coordinate) {
        this.end = coordinate;
    }

}
