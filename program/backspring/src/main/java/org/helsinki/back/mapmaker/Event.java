package org.helsinki.back.mapmaker;

public class Event implements Comparable<Event> {

    public static int SEEDCOORD = 0;
    public static int VERTEX = 1;

    Coordinate coordinate;
    int type;
    Parabola parabola;

    public Event(Coordinate coordinate, int type) {
        this.coordinate = coordinate;
        this.type = type;
        // this.parabola = null;
    }

    public int compareTo(Event other) {
        int comparison = this.coordinate.getY() - other.coordinate.getY();
        if (comparison == 0) {
            comparison = this.coordinate.getX() - other.coordinate.getX();
        }

        return comparison;
    }

    public void setParabola(Parabola par) {
        this.parabola = par;
    }

    public String getCoords() {
        return Integer.toString(coordinate.getX()) + ", " + Integer.toString(coordinate.getY());
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Parabola getParabola() {
        return this.parabola;
    }

}