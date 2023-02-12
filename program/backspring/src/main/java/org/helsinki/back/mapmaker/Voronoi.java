package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Voronoi {

    private Parabola rootParabola;
    private ArrayList<Coordinate> coordinates;
    private int currentY;
    private ArrayList<Vertex> vertices;
    private int mapSize;

    public Voronoi(ArrayList<Coordinate> coordinates, int mapSize) {
        this.rootParabola = null;
        this.coordinates = coordinates;
        this.vertices = new ArrayList<>();
        this.mapSize = mapSize;
    }

    public void generate() {

        ArrayList<Edge> edges = new ArrayList<>();

        coordinates.sort((c1, c2) -> c2.getY() - c1.getY());
        PriorityQueue<Event> events = new PriorityQueue<>();

        for (Coordinate c : coordinates) {
            events.add(new Event(c, 0));
            System.out.println(c.getY());
        }

        while (!events.isEmpty()) {
            Event e = events.poll();
            currentY = e.getCoordinate().getY();
            if (e.type == Event.SEEDCOORD) {
                handleSeedPoint(e.getCoordinate());
            } else {
            }
        }
    }

    /**
     * What happens when sweepline encounters a seed point
     * 
     * @param coordinate
     */
    public void handleSeedPoint(Coordinate coordinate) {
        if (rootParabola == null) {
            rootParabola = new Parabola(coordinate);
            return;
        }
        Parabola newParabola = getParabolaFromX(coordinate.getX());

    }

    /**
     * Get the parabola that is currently above a given x coordinate
     * 
     * @param xCoord
     * @return parabola
     */
    public Parabola getParabolaFromX(int xCoord) {
        Parabola parabola = rootParabola;

        int x = 0;
        while (!parabola.isCoordinate()) {
            x = getEdgeX(parabola);
            if (x > xCoord) {
                parabola = parabola.getLeftChild();
            } else {
                parabola = parabola.getRightChild();
            }
        }
        return parabola;
    }

    /**
     * Get current x coordinate of an edge
     * @param parabola
     * @return
     */
    public int getEdgeX(Parabola parabola) {
        Parabola left = parabola.getLeftChild();
        Parabola right = parabola.getRightChild();

        // coordinates for left vertex point
        int x1 = left.getCoordinate().getX();
        int y1 = left.getCoordinate().getY();

        // coordinates for right vertex point
        int x2 = right.getCoordinate().getX();
        int y2 = right.getCoordinate().getY();

        System.out.println(x1 + ", " + y1 + " and " + x2 + ", " + y2);
        
        // calculate where parabolas intersect
        double a1 = 1.0 / (2 * (y1 - currentY));
        double a2 = 1.0 / (2 * (y2 - currentY));
        double b1 = -1.0 * x1 / (y1 - currentY);
        double b2 = -1.0 * x2 / (y2 - currentY);
        double c1 = (x1 * x1 + y1 * y1 - 1.0 * currentY * currentY) / (2.0 * (y1 - currentY));
        double c2 = (x2 * x2 + y2 * y2 - 1.0 * currentY * currentY) / (2.0 * (y2 - currentY));

        System.out.println(a1 + ", " + a2 + ", " + b1 + ", " + b2 + ", " + c1 + ", " + c2);
        double a = a1 - a2;
        double b = b1 - b2;
        double c = c1 - c2;
        double disc = b * b - 4 * a * c;

        System.out.println("a, b, c, disc: " + a + ", " + b + ", " + c + ", " + disc);

        double candidate1 = (-1 * b + Math.sqrt(disc)) / (2 * a);
        double candidate2 = (-1 * b - Math.sqrt(disc)) / (2 * a);

        System.out.println("candidates: " + candidate1 + ", " + candidate2);
        if (y1 > y2) {
            return (int) Math.max(candidate1, candidate2);
        }
        return (int) Math.min(candidate1, candidate2);
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    /**
     * For testing purposes, get current root parabola
     * 
     * @return rootParabola
     */
    public Parabola getRootParabola() {
        return rootParabola;
    }

    /**
     * For testing purposes, set currentY
     * @param y
     */
    public void setCurrentY(int y) {
        this.currentY = y;
    }

}
