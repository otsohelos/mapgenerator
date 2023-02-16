package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Creates coordinates according to parameters
 */
public class CoordinateCreator {

    private int mapSize;

    public CoordinateCreator(int mapSize) {
        this.mapSize = mapSize;
    }

    /**
     * Creates coordinates
     * @return
     */
    public ArrayList<Coordinate> createCoordinates() {
        Random rnd = new Random();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int i = 0; i < mapSize / 4; i++) {
            int x = rnd.nextInt(mapSize);
            int y = rnd.nextInt(mapSize);
            int acceptableDistance = mapSize / 8;
            Coordinate coordinate = new Coordinate(x, y);
            boolean coordinateIsOk = true;
            for (Coordinate c : coordinates) {
                if (Calculator.getCoordinateDistance(coordinate, c) < acceptableDistance) {
                    coordinateIsOk = false;
                }
            }
            if (coordinateIsOk) {
                coordinates.add(coordinate);
            }
        }
        return coordinates;
    }
}
