package org.helsinki.back;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.helsinki.back.mapmaker.Coordinate;
import org.junit.jupiter.api.Test;

public class CoordinateCreationTest {
    @Test
    void coordinateCreationTest() {
        Coordinate c = new Coordinate(.010, .008);

        assertTrue(c.getXString().equals("10.0"));
        assertTrue(c.getYString().equals("8.0"));
    }
}
