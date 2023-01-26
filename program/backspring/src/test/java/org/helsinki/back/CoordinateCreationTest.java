package org.helsinki.back;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.helsinki.back.mapmaker.Coordinate;
import org.junit.jupiter.api.Test;

public class CoordinateCreationTest {
    @Test
    void demoTestMethod() {
        Coordinate c = new Coordinate(10, 8);

        assertTrue(c.getX().equals("10"));
        assertTrue(c.getY().equals("8"));
    }
}
