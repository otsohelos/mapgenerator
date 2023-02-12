package org.helsinki.back;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.helsinki.back.mapmaker.Coordinate;
import org.helsinki.back.mapmaker.Parabola;
import org.helsinki.back.mapmaker.Voronoi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VoronoiTest {
    static Coordinate c1;
    static Coordinate c2;
    static Coordinate c3;
    static ArrayList<Coordinate> coordinates;
    static Voronoi voronoi;

    @BeforeAll
    static void setup() {
        c1 = new Coordinate(5, 15);
        c2 = new Coordinate(15, 10);
        c3 = new Coordinate(10, 18);
        coordinates = new ArrayList<>();

        coordinates.add(c1);
        coordinates.add(c2);
        voronoi = new Voronoi(coordinates, 25);
    }

    @Test
    void rootParabolaTest() {
        assertTrue(voronoi.getRootParabola() == null);
        voronoi.handleSeedPoint(c1);
        assertFalse(voronoi.getRootParabola() == null);
    }

    @Test
    void parabolaTypeTest() {
        Parabola p1 = new Parabola(c1);
        Parabola p2 = new Parabola();
        assertTrue(p1.isCoordinate());
        assertFalse(p2.isCoordinate());
    }

    @Test
    void parabolaIntersectionTest() {
        Parabola p1 = new Parabola(c1);
        Parabola p2 = new Parabola(c2);
        Parabola p3 = new Parabola(c3);
        p3.setLeftChild(p1);
        p3.setRightChild(p2);
        voronoi.setCurrentY(20);
        
        assertTrue(voronoi.getEdgeX(p3) == 10);
    }

}
