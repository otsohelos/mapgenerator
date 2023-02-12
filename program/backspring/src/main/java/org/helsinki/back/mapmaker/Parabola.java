package org.helsinki.back.mapmaker;

public class Parabola {
    private Coordinate coordinate;
    private boolean isCoordinate;
    private Parabola leftChild;
    private Parabola rightChild;
    private Parabola parent;
    private Event event;

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

    public Parabola getRightChild() {
        Parabola child = rightChild;
        if (child == null) {
            return child;
        }
        if (!child.isCoordinate) {
            child = child.getRightChild();
        }
        System.out.println("child type is coordinate? " + child.isCoordinate());
        return child;
    }

    public Parabola getLeftChild() {
        Parabola child = leftChild;
        if (child == null) {
            return child;
        }
        if (!child.isCoordinate) {
            child = child.getLeftChild();
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

}
