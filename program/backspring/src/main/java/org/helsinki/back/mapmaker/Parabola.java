package org.helsinki.back.mapmaker;

public class Parabola {
    private Coordinate coordinate;
    private boolean isCoordinate;
    private Parabola leftChild;
    private Parabola rightChild;
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

    public boolean isCoordinate() {
        return isCoordinate;
    }

    public void setIsCoordinate(boolean value) {
        this.isCoordinate = value;
    }

    public void setLeftChild(Parabola parabola) {
        leftChild = parabola;
        parabola.setParent(this);
    }

    public void setRightChild(Parabola parabola) {
        rightChild = parabola;
        parabola.parent = this;
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

    public Parabola getRightCoordinateChild() {
        Parabola child = rightChild;
        if (child == null) {
            return child;
        }
        if (!child.isCoordinate) {
            child = child.getRightCoordinateChild();
        }
        System.out.println("child type is coordinate? " + child.isCoordinate());
        return child;
    }

    public Parabola getLeftCoordinateChild() {
        Parabola child = leftChild;
        if (child == null) {
            return child;
        }
        if (!child.isCoordinate) {
            child = child.getLeftCoordinateChild();
        }
        System.out.println("child type is coordinate? " + child.isCoordinate());
        return child;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Parabola getParent() {
        return parent;
    }

    public Event getEvent() {
        return event;
    }

    public Parabola getLeftParent() {
        Parabola parentCandidate = parent;
        if (parentCandidate == null) {
            return null;
        }
        Parabola par = this;
        while (parent.getLeftChild() == par) {
            if (parentCandidate.getParent() == null) {
                return null;
            }
            par = parentCandidate;
            parentCandidate = parentCandidate.getParent();
        }
        return parentCandidate;
    }

    public Parabola getRightParent() {
        Parabola parentCandidate = parent;
        if (parentCandidate == null) {
            return null;
        }
        Parabola par = this;
        while (parent.getRightChild() == par) {
            if (parentCandidate.getParent() == null) {
                return null;
            }
            par = parentCandidate;
            parentCandidate = parentCandidate.getParent();
        }
        return parentCandidate;
    }

    public Edge getEdge() {
        return edge;
    }
}
