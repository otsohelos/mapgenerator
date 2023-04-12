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
     * 
     * @return
     */
    public ArrayList<Coordinate> createCoordinates() {
        Random rnd = new Random();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int i = 0;
        while (i < 30) {
            double x = rnd.nextDouble();
            double y = rnd.nextDouble();
            double acceptableDistance = 1.0 * mapSize / 15000;
            Coordinate coordinate = new Coordinate(x, y);
            boolean coordinateIsOk = true;
            for (Coordinate c : coordinates) {
                if (Calculator.getCoordinateDistance(coordinate, c) < acceptableDistance) {
                    coordinateIsOk = false;
                } else if (c.getX() == x || c.getY() == y) {
                    coordinateIsOk = false;
                }
            }
            if (coordinateIsOk) {
                coordinates.add(coordinate);
                i++;
            }
        }

        return coordinates;
    }
}
