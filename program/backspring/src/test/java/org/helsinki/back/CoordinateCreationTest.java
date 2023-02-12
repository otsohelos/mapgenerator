package org.helsinki.back;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.helsinki.back.mapmaker.Coordinate;
import org.junit.jupiter.api.Test;

public class CoordinateCreationTest {
    @Test
    void coordinateCreationTest() {
        Coordinate c = new Coordinate(10, 8);

        assertTrue(c.getXString().equals("10"));
        assertTrue(c.getYString().equals("8"));
    }
}
