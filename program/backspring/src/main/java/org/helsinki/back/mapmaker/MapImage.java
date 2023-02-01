package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Glass that generates the map image and conversts it to SVG string
 */
public class MapImage {

    /**
     * Image string creator
     * 
     * @return image string
     */
    public String getImageString() {

        int mapSize = 500;
        CoordinateCreator cc = new CoordinateCreator(mapSize);
        Calculator calculator = new Calculator();

        ArrayList<Coordinate> coordinates = cc.createCoordinates();

        String coordinateString = "";

        for (Coordinate coordinate : coordinates) {
            coordinateString = coordinateString
                    + "<circle cx='"
                    + coordinate.getX()
                    + "' cy='"
                    + coordinate.getY()
                    + "' r='5' stroke='black' stroke-width='0' fill='white' />";
        }

        ArrayList<Vertex> vertices = new ArrayList<>();

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                HashMap<Coordinate, Integer> distances = new HashMap<>();
                int lowestDistance = mapSize;
                for (Coordinate c : coordinates) {
                    int distance = calculator.getPointCoordinateDistance(c, i, j);
                    if (distance <= lowestDistance) {
                        distances.put(c, (int) Math.round(distance));
                        lowestDistance = distance;
                    }
                }
                if (Collections.frequency(distances.values(), lowestDistance) >= 3) {
                    double newLowest = lowestDistance;
                    List<Coordinate> vertexCoords = distances
                            .entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(entry.getValue(), newLowest))
                            .map(entry -> entry.getKey())
                            .collect(Collectors.toList());

                    vertices.add(new Vertex(i, j, vertexCoords));
                }

            }
        }
        System.out.println("Vertices:");

        for (Vertex v : vertices) {
            System.out.println(v.getCoordinatesString());
        }

        String vertexString = "";



        for (Vertex vertex : vertices) {
            coordinateString = coordinateString
                    + "<circle cx='"
                    + vertex.getXString()
                    + "' cy='"
                    + vertex.getYString()
                    + "' r='3' stroke='teal' stroke-width='0' fill='teal' />";
        }

        String imageString = "<svg  xmlns='http://www.w3.org/2000/svg' version='1.2' baseProfile='tiny' width='"
                + mapSize
                + "' height='"
                + mapSize
                + "'>"
                + coordinateString
                + "</svg>";

        return imageString;
    }

}
