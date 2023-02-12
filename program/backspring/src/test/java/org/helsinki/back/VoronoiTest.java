package org.helsinki.back;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.helsinki.back.mapmaker.Coordinate;
import org.helsinki.back.mapmaker.Voronoi;
import org.junit.jupiter.api.Test;

public class VoronoiTest {
    @Test
    void demoTestMethod() {
        Coordinate c1 = new Coordinate(10, 20);
        Coordinate c2 = new Coordinate(15, 10);

        ArrayList<Coordinate> coordinates = new ArrayList<>();

        coordinates.add(c1);
        coordinates.add(c2);
        Voronoi voronoi = new Voronoi(coordinates, 25);

        assertTrue(voronoi.getRootParabola()==null);
        voronoi.handleSeedPoint(c1);
    }
}
