package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Voronoi diagram creation main class
 */
public class Voronoi {

    private Parabola rootParabola;
    private ArrayList<Coordinate> coordinates;
    private int currentY;
    private ArrayList<Vertex> vertices;
    private int mapSize;
    private PriorityQueue<Event> events;
    private Calculator calculator;
    private ArrayList<Edge> edges;

    public Voronoi(ArrayList<Coordinate> coordinates, int mapSize) {
        this.rootParabola = null;
        this.coordinates = coordinates;
        this.vertices = new ArrayList<>();
        this.mapSize = mapSize;
        this.events = new PriorityQueue<>();
        this.calculator = new Calculator();
        this.edges = new ArrayList<>();

    }

    /**
     * Orchestrator for Voronoi diagram generation
     */
    public void generate() {
        long startTime = System.currentTimeMillis();

        coordinates.sort((c1, c2) -> c1.getY() - c2.getY());

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
                handleCircleEvent(e);
            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("Generating Voronoi took " + (endTime - startTime) + " ms.");
        System.out.println("We have " + edges.size() + " edges");
    }

    /**
     * What happens when sweepline encounters a circle event
     * 
     * @param e
     */
    private void handleCircleEvent(Event event) {
        Parabola par1 = event.getParabola();
        Parabola parent1 = par1.getLeftParent();
        Parabola parent2 = par1.getRightParent();
        Parabola child1 = parent1.getLeftCoordinateChild();
        Parabola child2 = parent2.getRightCoordinateChild();

        if (child1.getEvent() != null) {
            events.remove(child1.getEvent());
            child1.setEvent(null);
        }
        if (child2.getEvent() != null) {
            events.remove(child2.getEvent());
            child2.setEvent(null);
            ;
        }

        Coordinate coordinate = new Coordinate(event.getCoordinate().getX(),
                (int) calculator.getParabolaY(par1.getCoordinate(), event.getCoordinate().getX(), currentY));
        parent1.getEdge().setEnd(coordinate);
        parent2.getEdge().setEnd(coordinate);

        edges.add(parent1.getEdge());
        edges.add(parent2.getEdge());
        Parabola higher = new Parabola();
        Parabola p = par1;
        while (p != rootParabola) {
            p = p.getParent();
            if (p == parent1) {
                higher = parent1;
            }
            if (p == parent2) {
                higher = parent2;
            }
        }
        higher.setEdge(new Edge(coordinate, child1.getCoordinate(), child2.getCoordinate()));

        Parabola gparent = par1.getParent().getParent();
        if (par1.getParent().getLeftChild() == par1) {
            if (gparent.getLeftChild() == par1.getParent())
                gparent.setLeftChild(par1.getParent().getRightChild());
            if (gparent.getRightChild() == par1.getRightChild())
                gparent.setRightChild(par1.getParent().getRightChild());
        } else {
            if (gparent.getLeftChild() == par1.getParent())
                gparent.setLeftChild(par1.getParent().getLeftChild());
            if (gparent.getRightChild() == par1.getParent())
                gparent.setRightChild(par1.getParent().getLeftChild());
        }

        par1.setParent(null);
        par1 = null;

        conditionalAddCircleEvent(child1);
        conditionalAddCircleEvent(child2);
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
        Parabola newParabola = getParabolaAboveX(coordinate.getX());

        Event event = newParabola.getEvent();
        if (event != null) {
            events.remove(event);
            newParabola.setEvent(null);
        }

        // create edge that runs through parabola focus and p
        Coordinate startCoord = new Coordinate(coordinate.getX(),
                (int) calculator.getParabolaY(coordinate, coordinate.getX(), currentY));
        Edge leftEdge = new Edge(startCoord, newParabola.getCoordinate(), coordinate);
        Edge rightEdge = new Edge(startCoord, coordinate, newParabola.getCoordinate());
        leftEdge.setNeighbor(rightEdge);
        rightEdge.setNeighbor(leftEdge);
        newParabola.setEdge(leftEdge);
        newParabola.setIsCoordinate(false);

        // replace original parabola par with p0, p1, p2
        Parabola newPar1 = new Parabola(newParabola.getCoordinate());
        Parabola newPar2 = new Parabola(coordinate);
        Parabola newPar3 = new Parabola(newParabola.getCoordinate());

        newParabola.setLeftChild(newPar1);
        newParabola.setRightChild(new Parabola());
        newParabola.getRightChild().setEdge(rightEdge);
        newParabola.getRightChild().setLeftChild(newPar2);
        newParabola.getRightChild().setRightChild(newPar3);

        // check circle events
        conditionalAddCircleEvent(newPar1);
        conditionalAddCircleEvent(newPar3);

    }

    /**
     * Add new circle event if conditions are met
     * 
     * @param parabola
     */
    private void conditionalAddCircleEvent(Parabola parabola) {
        Parabola leftPar = parabola.getLeftParent();
        Parabola rightPar = parabola.getRightParent();

        if (leftPar == null || rightPar == null) {
            return;
        }

        Parabola a = leftPar.getLeftCoordinateChild();
        Parabola c = leftPar.getRightCoordinateChild();

        if (a == null || c == null || a.getCoordinate() == c.getCoordinate()) {
            return;
        }

        // if parabola points are not in the right order, do nothing
        if (Calculator.counterClockwise(a.getCoordinate(), parabola.getCoordinate(), c.getCoordinate()) != 1) {
            return;
        }

        Coordinate startCoordinate = Calculator.getEdgeIntersection(leftPar.getEdge(), rightPar.getEdge());
        if (startCoordinate == null)
            return;

        // circle radius
        double radX = parabola.getCoordinate().getX() - startCoordinate.getX();
        double radY = parabola.getCoordinate().getY() - startCoordinate.getY();
        double radius = Math.sqrt((radX * radX) + (radY * radY));

        // check that this coord isn't after sweepline
        if (startCoordinate.getY() + radius < currentY)
            return;

        Coordinate ep = new Coordinate((int) startCoordinate.getX(), (int) startCoordinate.getY() + (int) radius);

        // add circle event
        Event e = new Event(ep, 1);
        e.setParabola(parabola);
        parabola.setEvent(e);
        events.add(e);
    }

    /**
     * Get the parabola that is currently above a given x coordinate
     * 
     * @param xCoord
     * @return parabola
     */
    public Parabola getParabolaAboveX(int xCoord) {
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
     * 
     * @param parabola
     * @return
     */
    public int getEdgeX(Parabola parabola) {

        Parabola left = parabola == null ? null : parabola.getLeftCoordinateChild();
        Parabola right = parabola == null ? null : parabola.getRightCoordinateChild();

        // coordinates for left vertex point
        int x1 = left.getCoordinate().getX();
        int y1 = left.getCoordinate().getY();

        // coordinates for right vertex point
        int x2 = right.getCoordinate().getX();
        int y2 = right.getCoordinate().getY();

        System.out.println(x1 + ", " + y1 + " and " + x2 + ", " + y2);

        // calculate where parabolas intersect
        // todo move this to calculator class
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

    public ArrayList<Edge> getEdges() {
        return this.edges;
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
     * 
     * @param y
     */
    public void setCurrentY(int y) {
        this.currentY = y;
    }

}
