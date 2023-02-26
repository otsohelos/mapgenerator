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

    /**
     * Calculate y on parabola from focus point, sweepline, and x
     * 
     * @param coordinate
     * @param x
     * @param currentY
     * @return
     */
    public static double getParabolaY(Coordinate coordinate, int x, int currentY) {
        // checked
        double distance = 2 * (coordinate.getY() - currentY);
        double a = 1.0 / distance;
        double b = (-2.0 * coordinate.getX()) / distance;
        double c = (1.0 * coordinate.getX() * coordinate.getX() + 1.0 * coordinate.getY() * coordinate.getY() - 1.0 * currentY * currentY) / distance;
        return (a * x * x + b * x + c);
    }

    /**
     * Is a -> b -> c a counterclockwise turn
     * 
     * @param a first point
     * @param b second point
     * @param c third point
     * @return { -1, 0, +1 } if a->b->c is a { clockwise, collinear; counterclocwise
     *         } turn. Copied from
     *         https://algs4.cs.princeton.edu/91primitives/Point.java.html
     */
    public static int counterClockwise(Coordinate a, Coordinate b, Coordinate c) {
        // checked
        double first = b.getX() - a.getX();
        double second = c.getY() - a.getY();
        double third = b.getY() - a.getY();
        double fourth = c.getX() - a.getX();
        double area = (first * second) - (third * fourth);
        System.out.println(area);
        if (area < 0)
            return -1;
        else if (area > 0)
            return 1;
        else
            return 0;
    }

    public static Coordinate getEdgeIntersection(Edge a, Edge b) {
        if (b.getSlope() == a.getSlope() && b.getYIntercept() != a.getYIntercept()) {
            return null;
        }
        double x = (b.getYIntercept() - a.getYIntercept()) / (a.getSlope() - b.getSlope());
        double y = (a.getSlope() * x) + a.getYIntercept();
        Coordinate c = new Coordinate((int) Math.round(x), (int) Math.round(y));
        return c;
    }
}
