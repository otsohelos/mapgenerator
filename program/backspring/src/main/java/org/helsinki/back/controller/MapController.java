package org.helsinki.back.controller;

import org.helsinki.back.links.MapLinks;
import org.helsinki.back.mapmaker.MapImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")

/**
 * Controller class for APi call operations
 */
public class MapController {
    @Autowired
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = MapLinks.GET_MAP)
    /**
     * Simpe operation for getting map, no params for now
     * @return map as SVG string
     */
    public ResponseEntity<?> getMap() {

        System.out.println("Map requested");

        MapImage mi = new MapImage();

        String res = mi.getImageString();
        return ResponseEntity.ok(res);
    }

    // todo POST method with params
}
