package org.helsinki.back.mapmaker;

public class Parabola {
    private Coordinate coordinate;
    private boolean isCoordinate;
    Parabola leftChild;
    Parabola rightChild;
    private Parabola parent;
    private Event event;
    private Edge edge;

    public Parabola(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.isCoordinate = true;
    }

    public Parabola() {
        this.isCoordinate = false;
    }

    public void setIsCoordinate(boolean value) {
        isCoordinate = value;
    }

    public void setLeftChild(Parabola p) {
        leftChild = p;
        p.setParent(this);
    }

    public void setRightChild(Parabola p) {
        rightChild = p;
        p.setParent(this);
    }

    public void setParent(Parabola parabola) {
        parent = parabola;
    }

    public void setEvent(Event e) {
        event = e;
    }

    public void setEdge(Edge e) {
        this.edge = e;
    }

    public Parabola getLeftChild() {
        return this.leftChild;
    }

    public Parabola getRightChild() {
        return this.rightChild;
    }

    /**
     * Get closest child, grandchild etc. that is coordinate type
     * 
     * @param p
     * @return
     */
    public static Parabola getLeftCoordinateChild(Parabola p) {
        if (p == null) {
            return null;
        }
        Parabola child = p.getLeftChild();
        while (!child.isCoordinate()) {
            child = child.getRightChild();
        }
        return child;
    }

    /**
     * Get closest child, grandchild etc. that is coordinate type
     * 
     * @param p
     * @return
     */
    public static Parabola getRightCoordinateChild(Parabola p) {
        if (p == null) {
            return null;
        }
        Parabola child = p.getRightChild();
        while (!child.isCoordinate()) {
            child = child.getLeftChild();
        }
        return child;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Parabola getParent() {
        return this.parent;
    }

    public Event getEvent() {
        return event;
    }

    /**
     * Get the closest parent for which a given parabola is a right child (or
     * grandchild etc.)
     * 
     * @param parabola
     * @return parent parabola
     */
    public static Parabola getLeftParent(Parabola parabola) {
        Parabola parent = parabola.getParent();
        if (parent == null)
            return null;
        Parabola last = parabola;
        while (parent.getLeftChild() == last) {
            if (parent.getParent() == null)
                return null;
            last = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * Get the closest parent for which a given parabola is a left child (or
     * grandchild etc.)
     * 
     * @param parabola
     * @return parent parabola
     */
    public static Parabola getRightParent(Parabola parabola) {
        Parabola parent = parabola.getParent();
        if (parent == null)
            return null;
        Parabola last = parabola;
        while (parent.getRightChild() == last) {
            if (parent.getParent() == null)
                return null;
            last = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    public Edge getEdge() {
        return edge;
    }

    public boolean isCoordinate() {
        return isCoordinate;
    }
}
