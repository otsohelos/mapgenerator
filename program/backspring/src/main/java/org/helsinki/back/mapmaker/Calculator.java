package org.helsinki.back.mapmaker;

public class Calculator {
        /**
     * Calculates distance between two coordinates to avoid doordinates being too close
     * @param a coordinate
     * @param b coordinate
     * @return distance
     */
    public double getCoordinateDistance(Coordinate a, Coordinate b) {
        double distance = Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));

        return distance;
    }

    public int getPointCoordinateDistance(Coordinate c, int x, int y) {
        double distance = Math.sqrt(Math.pow((c.getX() - x), 2) + Math.pow((c.getY() - y), 2));
        return (int) Math.round(distance);
    }
}
