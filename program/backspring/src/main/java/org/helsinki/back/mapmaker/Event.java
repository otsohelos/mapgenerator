package org.helsinki.back.mapmaker;

public class Event implements Comparable<Event> {

    public static int SEEDCOORD = 0;
    public static int VERTEX = 1;

    Coordinate coordinate;
    int type;
    // Parabola parabola; // only if this is a vertex event

    public Event(Coordinate coordinate, int type) {
        this.coordinate = coordinate;
        this.type = type;
        // this.parabola = null;
    }

    public int compareTo(Event other) {
        return this.coordinate.getX() - other.coordinate.getX();
    }

    public String getCoords() {
        return Integer.toString(coordinate.getX()) + ", " + Integer.toString(coordinate.getY());
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }
}