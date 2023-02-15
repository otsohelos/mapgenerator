package org.helsinki.back.mapmaker;

/**
 * Class for static methods that are just math operations
 */
public class Calculator {
    /**
     * Calculates distance between two coordinates to avoid doordinates being too
     * close
     * 
     * @param a coordinate
     * @param b coordinate
     * @return distance
     */
    public static double getCoordinateDistance(Coordinate a, Coordinate b) {
        double distance = Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));

        return distance;
    }

    public int getPointCoordinateDistance(Coordinate c, int x, int y) {
        double distance = Math.sqrt(Math.pow((c.getX() - x), 2) + Math.pow((c.getY() - y), 2));
        return (int) distance;
    }

    /**
     * Calculate y on parabola from focus point, sweepline, and x
     * 
     * @param coordinate
     * @param x
     * @param sweepY
     * @return
     */
    public double getParabolaY(Coordinate coordinate, int x, int sweepY) {

        double distance = coordinate.getY() - sweepY;
        double a1 = 2.0 / distance;
        double b1 = -1.0 * coordinate.getX() / distance;
        double c1 = (coordinate.getX() * coordinate.getX() + coordinate.getY() * coordinate.getY() - sweepY * sweepY)
                / distance;
        return (a1 * x * x + b1 * x + c1);
    }

    /**
     * Is a->b->c a counterclockwise turn
     * 
     * @param a first point
     * @param b second point
     * @param c third point
     * @return { -1, 0, +1 } if a->b->c is a { clockwise, collinear; counterclocwise
     *         } turn.
     *         Copied from
     *         https://algs4.cs.princeton.edu/91primitives/Point.java.html
     */
    public static int counterClockwise(Coordinate a, Coordinate b, Coordinate c) {
        double area = (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
        if (area < 0)
            return -1;
        else if (area > 0)
            return +1;
        else
            return 0;
    }

    public static Coordinate getEdgeIntersection(Edge a, Edge b) {
        if (b.getSlope() == a.getSlope() && b.getIntercept() != a.getIntercept())
            return null;

        double x = (b.getIntercept() - a.getIntercept()) / (a.getSlope() - b.getSlope());
        double y = a.getSlope() * x + a.getSlope();

        return new Coordinate((int) x, (int) y);
    }
}
