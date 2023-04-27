package org.helsinki.back.mapmaker;

import java.util.ArrayList;

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

        int mapSize = 1000;
        CoordinateCreator cc = new CoordinateCreator(mapSize);


        ArrayList<Coordinate> coordinates = cc.createCoordinates();

        long startTime = System.currentTimeMillis();

        Voronoi voronoi = new Voronoi(coordinates, mapSize);
        System.out.println(voronoi);
        voronoi.generate();

        ArrayList<Edge> edges = voronoi.getEdges();
        String edgeString = "";

        for (Edge edge : edges) {
            edgeString = edgeString
                    + "<line x1='"
                    + edge.getStartX()
                    + "' y1='"
                    + edge.getStartY()
                    + "' x2='"
                    + edge.getEndX()
                    + "' y2='"
                    + edge.getEndY()
                    + "' style='stroke:rgb(255,0,0);stroke-width:2' />";

        }
        String coordString = "";
        for (Coordinate coordinate : coordinates) {
            coordString = coordString + "<circle cx='"
                    + coordinate.getXString()
                    + "' cy='"
                    + coordinate.getYString()
                    + "' r='5' stroke='green' stroke-width='0' fill='white' />";
        }

        String imageString = "<svg  xmlns='http://www.w3.org/2000/svg' version='1.2' baseProfile='tiny' width='"
                + mapSize
                + "' height='"
                + mapSize
                + "'>"
                + coordString
                + edgeString
                + "</svg>";

        long endTime = System.currentTimeMillis();
        System.out.println("Generating voronoi and map image took " + (endTime - startTime) + " ms.");

        return imageString;
    }
}
