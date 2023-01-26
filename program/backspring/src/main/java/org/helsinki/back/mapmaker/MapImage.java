package org.helsinki.back.mapmaker;

import java.util.ArrayList;
import java.util.Random;

/**
 * Glass that generates the map image and conversts it to SVG string
 */
public class MapImage {

    /**
     * Image string creator
     * This is a large mishmash of a class for now, to be divided into smaler components
     * 
     * @return image sting
     */
    public String getImageString() {
        Random rnd = new Random();
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        int mapSize = 500;

        for (int i = 0; i < 10; i++) {
            int x = rnd.nextInt(mapSize);
            int y = rnd.nextInt(mapSize);
            Coordinate coordinate = new Coordinate(x, y);
            coordinates.add(coordinate);
        }

        String coordinateString = "";

        for (Coordinate coordinate: coordinates) {
            coordinateString = coordinateString 
            + "<circle cx='"
            + coordinate.getX()
            + "' cy='"
            + coordinate.getY()
            + "' r='5' stroke='black' stroke-width='0' fill='white' />";
        }

        String imageString = "<svg  xmlns='http://www.w3.org/2000/svg' version='1.2' baseProfile='tiny' width='"
         + mapSize 
         + "' height='"
         + mapSize
         +"'>"
         + coordinateString
         +"</svg>";
        return imageString;
    }

}
