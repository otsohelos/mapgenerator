package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Voronoi diagram creation main class
 */
public class Voronoi {

    private Parabola rootParabola;
    private ArrayList<Coordinate> coordinates;
    private ArrayList<Event> handledEvents;

    private int currentY;
    private ArrayList<Vertex> vertices;
    private int mapSize;
    private PriorityQueue<Event> events;
    private ArrayList<Edge> edges;
    private int circleEvents;

    public Voronoi(ArrayList<Coordinate> coordinates, int mapSize) {
        this.rootParabola = null;
        this.coordinates = coordinates;
        this.vertices = new ArrayList<>();
        this.handledEvents = new ArrayList<>();
        this.mapSize = mapSize;
        this.events = new PriorityQueue<>();
        this.edges = new ArrayList<>();
        this.circleEvents = 0;
    }

    /**
     * Orchestrator for Voronoi diagram generation
     */
    public void generate() {
        long startTime = System.currentTimeMillis();

        // coordinates.sort((c1, c2) -> c1.getY() - c2.getY());
        System.out.println(coordinates.size());
        for (Coordinate c : coordinates) {
            events.add(new Event(c, Event.SEEDCOORD));
        }
        int count = 0;

        while (!events.isEmpty()) {
            Event e = events.poll();
            handledEvents.add(e);
            currentY = e.getCoordinate().getY();
            count++;
            if (e.type == Event.SEEDCOORD) {
                handleSeedPoint(e.getCoordinate());
                String unfinishedMapString = visualizeUnfinishedMap();
                //System.out.println(unfinishedMapString);
                //System.out.println();
            } else {
                handleCircleEvent(e);
                String unfinishedMapString = visualizeUnfinishedMap();
                //System.out.println(unfinishedMapString);
                //System.out.println();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Generating Voronoi took " + (endTime - startTime) + " ms.");
        System.out.println("Coordinates: " + coordinates.size());
        System.out.println("We have " + edges.size() + " edges");
        System.out.println(circleEvents + " circle events");

        endEdges(rootParabola);

        for (Edge edge : edges) {
            if (edge.getNeighbor() != null) {
                edge.setStart(edge.getNeighbor().getEnd());
                edge.setNeighbor(null);
                // System.out.println("edge from " + edge.getStartX() + ", " + edge.getStartY()
                // + " to " + edge.getEndX() + ", " + edge.getEndY());
            }
        }
    }

    private String visualizeUnfinishedMap() {
        String edgeString = "";
        for (Edge edge : edges) {
            if (edge.getStartX() == null) {
                System.out.println("no start");
                break;
            }
            if (edge.getEndX() == null) {
                System.out.println("no end");
                break;
            }
            edgeString = edgeString + "<line x1='" + edge.getStartX() + "' y1='" + edge.getStartY() + "' x2='"
                    + edge.getEndX() + "' y2='" + edge.getEndY() + "' style='stroke:rgb(255,0,0);stroke-width:2' />";
        }
        String eventString = "";

        for (Event e: handledEvents) {
            String color = e.type == Event.CIRCLE ?  "teal" : "black";

            eventString = eventString
            + "<circle cx='"
            + e.getCoordinate().getX()
            + "' cy='"
            + e.getCoordinate().getY()
            + "' r='5' stroke='black' stroke-width='0' fill='"
            + color
            + "' />";
        }

        String mapString =  "<svg  xmlns='http://www.w3.org/2000/svg' version='1.2' baseProfile='tiny' width='" 
        + mapSize
                + "' height='" 
                + mapSize + "'>" 
                + edgeString 
                + eventString
                + "</svg>";
        return mapString;
    }

    /**
     * What happens when sweepline encounters a seed point
     * 
     * @param coordinate
     */
    public void handleSeedPoint(Coordinate coordinate) {

        //System.out.println("Handling seed  event at " + coordinate.toString());
        if (rootParabola == null) {
            rootParabola = new Parabola(coordinate);
            return;
        }
        Parabola parabolaAbove = getParabolaAboveX(coordinate.getX());

        if (parabolaAbove.getEvent() != null) {
            events.remove(parabolaAbove.getEvent());
            parabolaAbove.setEvent(null);
        }

        // create edges that run through parabola focus and coordinate
        Coordinate startCoord = new Coordinate(coordinate.getX(),
                (int) Math.round(Calculator.getParabolaY(parabolaAbove.getCoordinate(), coordinate.getX(), currentY)));
        // System.out.println(startCoord.toString());
        Edge leftEdge = new Edge(startCoord, parabolaAbove.getCoordinate(), coordinate);
        Edge rightEdge = new Edge(startCoord, coordinate, parabolaAbove.getCoordinate());

        leftEdge.setNeighbor(rightEdge);
        rightEdge.setNeighbor(leftEdge);
        parabolaAbove.setEdge(leftEdge);
        parabolaAbove.setIsCoordinate(false);

        // replace original parabola par with p0, p1, p2
        Parabola newPar1 = new Parabola(parabolaAbove.getCoordinate());
        Parabola newPar2 = new Parabola(coordinate);
        Parabola newPar3 = new Parabola(parabolaAbove.getCoordinate());

        parabolaAbove.setLeftChild(newPar1);
        parabolaAbove.setRightChild(new Parabola());
        parabolaAbove.getRightChild().setEdge(rightEdge);
        parabolaAbove.getRightChild().setLeftChild(newPar2);
        parabolaAbove.getRightChild().setRightChild(newPar3);

        // check circle events
        // System.out.print("par 1: ");
        conditionalAddCircleEvent(newPar1);
        // System.out.println();
        // System.out.print("par 3:");
        conditionalAddCircleEvent(newPar3);
    }

    /**
     * What happens when sweepline encounters a circle event
     * 
     * @param e
     */
    private void handleCircleEvent(Event event) {
        circleEvents++;
        //System.out.println("Handling circle event at " + event.getCoordinate().toString());
        Parabola par1 = event.getParabola();
        Parabola leftParent = Parabola.getLeftParent(par1);
        Parabola rightParent = Parabola.getRightParent(par1);
        Parabola child1 = Parabola.getLeftCoordinateChild(leftParent);
        Parabola child2 = Parabola.getRightCoordinateChild(rightParent);

        if (child1.getEvent() != null) {
            events.remove(child1.getEvent());
            child1.setEvent(null);
        }
        if (child2.getEvent() != null) {
            events.remove(child2.getEvent());
            child2.setEvent(null);
        }

        Coordinate coordinate = new Coordinate(event.getCoordinate().getX(),
                (int) Calculator.getParabolaY(par1.getCoordinate(), event.getCoordinate().getX(), currentY));

        leftParent.getEdge().setEnd(coordinate);
        rightParent.getEdge().setEnd(coordinate);
        edges.add(leftParent.getEdge());
        edges.add(rightParent.getEdge());

        Parabola higher = new Parabola();
        Parabola p = par1;
        while (p != rootParabola) {
            p = p.getParent();
            if (p == leftParent) {
                higher = leftParent;
            } if (p == rightParent) {
                higher = rightParent;
            }
        }

        Edge edge = new Edge(coordinate, child1.getCoordinate(), child2.getCoordinate());
        higher.setEdge(edge);

        Parabola grandparent = par1.getParent().getParent();
        if (par1.getParent().getLeftChild() == par1) {
            if (grandparent.getLeftChild() == par1.getParent()) {
                grandparent.setLeftChild(par1.getParent().getRightChild());
            }
            if (grandparent.getRightChild() == par1.getRightChild()) {
                grandparent.setRightChild(par1.getParent().getRightChild());
            }
        } else {
            if (grandparent.getLeftChild() == par1.getParent()) {
                grandparent.setLeftChild(par1.getParent().getLeftChild());
            }
            if (grandparent.getRightChild() == par1.getParent()) {
                grandparent.setRightChild(par1.getParent().getLeftChild());
            }
        }

        par1.setParent(null);
        par1 = null;

        // System.out.print("child 1: ");
        conditionalAddCircleEvent(child1);
        // System.out.println();
        // System.out.print("child 2: ");
        conditionalAddCircleEvent(child2);
        // System.out.println();
    }

    /**
     * Add new circle event if conditions are met
     * 
     * @param b
     */
    private void conditionalAddCircleEvent(Parabola b) {

        Parabola leftPar = Parabola.getLeftParent(b);
        Parabola rightPar = Parabola.getRightParent(b);

        if (leftPar == null || rightPar == null) {
            return;
        }

        Parabola a = Parabola.getLeftCoordinateChild(leftPar);
        Parabola c = Parabola.getRightCoordinateChild(rightPar);

        if (a == null || c == null || a.getCoordinate() == c.getCoordinate()) {
            return;
        }

        // if parabola points are not in the right order, do nothing
        if (Calculator.counterClockwise(a.getCoordinate(), b.getCoordinate(), c.getCoordinate()) != 1) {
            return;
        }

        Coordinate startCoordinate = Calculator.getEdgeIntersection(leftPar.getEdge(), rightPar.getEdge());
        if (startCoordinate == null) {
            return;
        }

        // calculate circle radius
        double deltaX = b.getCoordinate().getX() - startCoordinate.getX();
        double deltaY = b.getCoordinate().getY() - startCoordinate.getY();
        double radius = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        // check that this coord isn't below sweepline
        if (startCoordinate.getY() + radius < currentY) {
            return;
        }

        Coordinate circleCoord = new Coordinate(startCoordinate.getX(), (startCoordinate.getY() + (int) Math.round(radius)));

        // add circle event
        Event e = new Event(circleCoord, Event.CIRCLE);
        e.setParabola(b);
        b.setEvent(e);
        events.add(e);

        //System.out.println("circle event at " + circleCoord.toString() + " from parabola " + b.getCoordinate().toString());
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
        Parabola left = Parabola.getLeftCoordinateChild(parabola);
        Parabola right = Parabola.getRightCoordinateChild(parabola);

        // coordinates for left vertex point
        int xLeft = left.getCoordinate().getX();
        int yLeft = left.getCoordinate().getY();

        // coordinates for right vertex point
        int xRight = right.getCoordinate().getX();
        int yRight = right.getCoordinate().getY();

        // System.out.println(x1 + ", " + y1 + " and " + x2 + ", " + y2);

        // calculate where parabolas intersect
        // todo move this to calculator class
        double dp = 2 * (yLeft - currentY);
        double dp2 = 2 * (yRight - currentY);

        double a1 = 1.0 / dp;
        double b1 = -2.0 * xLeft / dp;
        double c1 = 1.0 * (xLeft * xLeft + yLeft * yLeft - currentY * currentY) / dp;

        double a2 = 1.0 / dp2;
        double b2 = -2.0 * xRight / dp2;
        double c2 = 1.0 * (xRight * xRight + yRight * yRight - currentY * currentY) / dp2;

        // System.out.println(a1 + ", " + a2 + ", " + b1 + ", " + b2 + ", " + c1 + ", "
        // + c2);
        double a = a1 - a2;
        double b = b1 - b2;
        double c = c1 - c2;
        double disc = b * b - (4 * a * c);

        double candidate1 = (-1 * b + Math.sqrt(disc)) / (2 * a);
        double candidate2 = (-1 * b - Math.sqrt(disc)) / (2 * a);

        // System.out.println("candidates: " + candidate1 + ", " + candidate2);
        if (yLeft > yRight) {
            return (int) Math.round(Math.max(candidate1, candidate2));
        }
        return (int) Math.round(Math.min(candidate1, candidate2));
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Finalize edges by recursivelly going through all parabolas
     * 
     * @param parabola
     */
    private void endEdges(Parabola parabola) {
        if (parabola.isCoordinate()) {
            parabola = null;
            return;
        }

        int x = getEdgeX(parabola);
        double y = (parabola.getEdge().getSlope() * x) + parabola.getEdge().getYIntercept();
        Coordinate coordinate = new Coordinate(x, (int) Math.round(y));
        parabola.getEdge().setEnd(coordinate);
        edges.add(parabola.getEdge());

        endEdges(parabola.getLeftChild());
        endEdges(parabola.getRightChild());

        parabola = null;
    }

    /**
     * For testing purposes, get current root parabola
     * 
     * @return rootParabola
     */
    public Parabola getRootParabola() {
        return this.rootParabola;
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
