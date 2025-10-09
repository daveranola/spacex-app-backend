package com.example.spacex_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SpaceXController {
    private final SpaceXService service;

    public SpaceXController(SpaceXService service) {
        this.service = service;
    }


    //@PathVariable used to extract values from the URL path and bind them to method parameters
    @GetMapping("/rockets")
    public List<RocketDto> getAllRockets() {
        return service.getAllRockets();
    }
}
