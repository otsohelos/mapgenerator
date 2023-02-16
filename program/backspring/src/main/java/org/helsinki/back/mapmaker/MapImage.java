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

        String coordinateString = "";

        for (Coordinate coordinate : coordinates) {
            coordinateString = coordinateString
                    + "<circle cx='"
                    + coordinate.getX()
                    + "' cy='"
                    + coordinate.getY()
                    + "' r='5' stroke='black' stroke-width='0' fill='white' />";
        }

        Voronoi voronoi = new Voronoi(coordinates, mapSize);
        voronoi.generate();

        /*
         * for (int i = 0; i < mapSize; i++) {
         * for (int j = 0; j < mapSize; j++) {
         * HashMap<Coordinate, Integer> distances = new HashMap<>();
         * int lowestDistance = mapSize;
         * for (Coordinate c : coordinates) {
         * int distance = calculator.getPointCoordinateDistance(c, i, j);
         * if (distance <= lowestDistance) {
         * distances.put(c, distance);
         * lowestDistance = distance;
         * }
         * }
         * if (Collections.frequency(distances.values(), lowestDistance) >= 3) {
         * int newLowest = lowestDistance;
         * List<Coordinate> vertexCoords = distances
         * .entrySet()
         * .stream()
         * .filter(entry -> entry.getValue() == newLowest)
         * .map(entry -> entry.getKey())
         * .collect(Collectors.toList());
         * Vertex newVertex = new Vertex(i, j, vertexCoords);
         * vertices.add(newVertex);
         * for (Coordinate c : vertexCoords) {
         * c.addVertex(newVertex);
         * }
         * }
         * }
         * }
         * System.out.println(vertices.size() + " vertices:");
         * 
         * for (Vertex v : vertices) {
         * System.out.println(v.getCoordinatesString());
         * }
         */
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

        // String polygonString = "";

        /*
         * for (Polygon polygon: polygons) {
         * polygonString = polygonString + "<polygon points=";
         * while (!pi.isDone()) {
         * System.out.println(pi.SEG_LINETO);
         * pi.next();
         * }
         * polygonString = polygonString + polygon.toString();
         * 
         * }
         */

        // System.out.println("polygons: " + polygonString);
        ArrayList<Edge> edges = voronoi.getEdges();
        String vertexString = "";

        for (Edge edge : edges) {
            vertexString = vertexString
                    + "<line x1='"
                    + edge.getStartX()
                    + "' y1='"
                    + edge.getStartY()

                    + "' x2='"
                    + edge.getEndX()
                    + "' y2='"
                    + edge.getEndY()
                    + "' style='stroke:rgb(255,0,0);stroke-width:2' />";

            /*
             * + "<circle cx='"
             * + edge.getStartX()
             * + "' cy='"
             * + edge.getStartY()
             * + "' r='3' stroke='teal' stroke-width='0' fill='teal' />"
             * + "<circle cx='"
             * + edge.getEndX()
             * + "' cy='"
             * + edge.getEndY()
             * + "' r='3' stroke='red' stroke-width='0' fill='red' />";
             */
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
