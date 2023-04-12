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

        ArrayList<Coordinate> coordinates = cc.createCoordinates();//new ArrayList<>();

        String coordinateString = "";

        /*for (Coordinate coordinate : coordinates) {
            coordinateString = coordinateString
                    + "<circle cx='"
                    + coordinate.getX()
                    + "' cy='"
                    + coordinate.getY()
                    + "' r='5' stroke='black' stroke-width='0' fill='white' />";
        }*/

        Coordinate a = new Coordinate(0, 200);
		Coordinate b = new Coordinate(200,105);
		Coordinate c = new Coordinate(300,400);
		Coordinate d = new Coordinate(600,800);
		Coordinate e = new Coordinate(610,100);
		Coordinate f = new Coordinate(700,910);
		Coordinate f3 = new Coordinate(710,910);
		Coordinate g = new Coordinate(900,900);
		Coordinate h = new Coordinate(100,500);
		Coordinate i = new Coordinate(400,410);
        Coordinate a2 = new Coordinate(120, 220);
		Coordinate b2 = new Coordinate(320,125);
		Coordinate c2 = new Coordinate(420,420);
		Coordinate d2 = new Coordinate(520,820);
		Coordinate e2 = new Coordinate(530,120);
		Coordinate f2 = new Coordinate(620,930);
		Coordinate g2 = new Coordinate(920,920);
		Coordinate h2 = new Coordinate(220,520);
		Coordinate i2 = new Coordinate(520,430);

        /*coordinates.add(a);
        coordinates.add(b);
        coordinates.add(c);
        coordinates.add(d);
        coordinates.add(e);
        coordinates.add(f);
        coordinates.add(f3);
        coordinates.add(g);
        coordinates.add(h);
        coordinates.add(i);
        coordinates.add(a2);
        coordinates.add(b2);
        coordinates.add(c2);
        coordinates.add(d2);
        coordinates.add(e2);
        coordinates.add(f2);
        coordinates.add(g2);
        coordinates.add(h2);
        coordinates.add(i2);*/

        Voronoi voronoi = new Voronoi(coordinates, mapSize);
        System.out.println(voronoi);
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

        /*for (Coordinate coordinate : coordinates) {
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
        }*/

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
        String coordString = "";
        for (Coordinate coordinate: coordinates)
        {
            coordString = coordString + "<circle cx='"
            + coordinate.getX()
            +"' cy='"
            + coordinate.getY()
            +  "' r='5' stroke='green' stroke-width='0' fill='white' />";
        }
        //System.out.println(coordString);

        String imageString = "<svg  xmlns='http://www.w3.org/2000/svg' version='1.2' baseProfile='tiny' width='"
                + mapSize
                + "' height='"
                + mapSize
                + "'>"
                + coordString
                + vertexString
                + "</svg>";

        return imageString;
    }

}
