package com.example.spacex_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/launches/upcoming")
    public List<LaunchDto> upcoming() {
        return service.listUpcoming();
    }

    @GetMapping("/launches/{id}")
    public LaunchDto byId(@PathVariable String id) {
        return service.getLaunch(id);
    }

    @GetMapping("/rockets/{id}")
    public RocketDto rocket(@PathVariable String id) {
        return service.getRocket(id);
    }
}
