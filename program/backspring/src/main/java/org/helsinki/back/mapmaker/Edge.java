package org.helsinki.back.mapmaker;

/**
 * Edge on Voronoi diagram
 */
public class Edge {
    private Coordinate start;
    private Coordinate end;
    private Edge neighbor;
    private double slope;
    private Coordinate midpoint;
    private double yIntercept;

    public Edge(Coordinate start, Coordinate left, Coordinate right) {
        this.start = start;
        this.slope = 1.0 * (right.getX() - left.getX()) / (left.getY() - right.getY());
        this.midpoint = new Coordinate((right.getX() + left.getX()) / 2, (left.getY() + right.getY()) / 2);
        this.yIntercept = midpoint.getY() - slope * midpoint.getX();
    }

    public void setNeighbor(Edge edge) {
        this.neighbor = edge;
    }

    public void setEnd(Coordinate coordinate) {
        this.end = coordinate;
    }

    public void setStart(Coordinate coordinate) {
        this.start = coordinate;
    }

    public Coordinate getEnd() {
        return this.end;
    }
    public Coordinate getStart() {
        return this.start;
    }

    public double getSlope() {
        return slope;
    }

    public String getStartX() {
        return this.start.getXString();
    }

    public String getStartY() {
        return this.start.getYString();
    }

    public String getEndX() {
        return this.end.getXString();
    }

    public String getEndY() {
        return this.end.getYString();
    }

    public Edge getNeighbor() {
        return this.neighbor;
    }

    public double getYIntercept() {
        return this.yIntercept;
    }

}
