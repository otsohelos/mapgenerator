package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.util.SystemPropertyUtils;


import ch.qos.logback.core.joran.spi.NewRuleProvider;

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
                        distances.put(c, distance);
                        lowestDistance = distance;
                    }
                }
                if (Collections.frequency(distances.values(), lowestDistance) >= 3) {
                    int newLowest = lowestDistance;
                    List<Coordinate> vertexCoords = distances
                            .entrySet()
                            .stream()
                            .filter(entry -> entry.getValue() == newLowest)
                            .map(entry -> entry.getKey())
                            .collect(Collectors.toList());
                    Vertex newVertex = new Vertex(i, j, vertexCoords);
                    vertices.add(newVertex);
                    for (Coordinate c : vertexCoords) {
                        c.addVertex(newVertex);
                    }
                }
            }
        }
        System.out.println(vertices.size() + " vertices:");

        for (Vertex v : vertices) {
            System.out.println(v.getCoordinatesString());
        }
        ArrayList<Polygon> polygons = new ArrayList<>();

        for (Coordinate coordinate : coordinates) {
            int numberOfCoords = coordinate.getVertices().size();
            int[] xs = new int[numberOfCoords];
            int[] ys = new int[numberOfCoords];
            int i = 0;
            for (Vertex v : coordinate.getVertices()) {
                xs[i] = v.getX();
                ys[i] = v.getY();
                i++;
            }
            polygons.add(new Polygon(xs, ys, numberOfCoords));
        }
        String polygonString = "";

        for (Polygon polygon: polygons) {
            polygonString = polygonString + "<polygon points=";
            while (!pi.isDone()) {
                System.out.println(pi.SEG_LINETO);
                pi.next();
            }
            polygonString = polygonString + polygon.toString();

        }

        System.out.println("polygons: " + polygonString);
        String vertexString = "";
        
        for (Vertex vertex : vertices) {
            vertexString = vertexString
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
                + vertexString
                + "</svg>";

        return imageString;
    }

}
