package org.helsinki.back.controller;
import org.helsinki.back.links.MapLinks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")

public class MapController {
    @Autowired
    
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = MapLinks.GET_MAP)
    public ResponseEntity<?> getMap() {

        System.out.println("Map requested");
        
        String res = "Hello World";
        return ResponseEntity.ok(res);
    }
	
	//todo POST method with params
}
