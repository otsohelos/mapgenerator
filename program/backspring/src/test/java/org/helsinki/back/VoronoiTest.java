package org.helsinki.back;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    static ArrayList<Coordinate> coordinates2;
    static Voronoi voronoi;

    @BeforeAll
    static void setup() {
        c1 = new Coordinate(.005, .015);
        c2 = new Coordinate(.015, .010);
        c3 = new Coordinate(.020, .018);
        
        coordinates = new ArrayList<>();

        coordinates.add(c1);
        coordinates.add(c2);

        coordinates2 = new ArrayList<>();


        coordinates2.add(c1);
        coordinates2.add(c2);
        coordinates2.add(c3);
    }

    @Test
    void rootParabolaTest() {
        voronoi = new Voronoi(coordinates, 25);
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
        voronoi = new Voronoi(coordinates, 25);
        Parabola p1 = new Parabola(c1);
        Parabola p2 = new Parabola(c2);
        Parabola p3 = new Parabola(c3);
        p3.setLeftChild(p1);
        p3.setRightChild(p2);
        voronoi.setCurrentY(20);
        System.out.println("edge: "+ voronoi.getEdgeX(p3));
        assertTrue(voronoi.getEdgeX(p3) == 4.728408350668825);
    }

    @Test
    void findParabolaAboveTest() {
        voronoi = new Voronoi(coordinates, 25);
        Parabola p1 = new Parabola(c2);
        Parabola p2 = new Parabola(c1);
        voronoi.setCurrentY(20);
        voronoi.handleSeedPoint(c1);
        voronoi.handleSeedPoint(c2);
        Parabola found1 = voronoi.getParabolaAboveX(24);
        Parabola found2 = voronoi.getParabolaAboveX(0);
        String str3 = found1.getCoordinate().toString();
        String str4 = p2.getCoordinate().toString();
        boolean same1 = str3.equals(str4);
        String str1 = found2.getCoordinate().toString();
        String str2 = p2.getCoordinate().toString();
        boolean same2 = str1.equals(str2);
        assertTrue(same1);
        assertTrue(same2);
    }
    @Test
    void voronoiGenerationTest() {
        voronoi = new Voronoi(coordinates, 25);
        int howManyEdges = voronoi.getEdges().size();
        assertTrue(howManyEdges == 0);
        voronoi.generate();
        howManyEdges = voronoi.getEdges().size();
        assertTrue(howManyEdges == 2);
    }
    @Test
    void moreComplexVoronoiGenerationTest() {
        voronoi = new Voronoi(coordinates2, 25);
        voronoi.generate();
        int howManyEdges = voronoi.getEdges().size();
        assertTrue(howManyEdges == 5);
    }
}
