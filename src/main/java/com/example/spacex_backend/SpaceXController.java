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

    @GetMapping("/history")
    public List<HistoricalDto> historicalEvents() {
        return service.historicalEvents();
    }

    @GetMapping("/launches/{id}")
    public HistoricalDto byId(@PathVariable String id) {
        return service.getLaunch(id);
    }

    @GetMapping("/rockets/{id}")
    public RocketDto rocket(@PathVariable String id) {
        return service.getRocket(id);
    }
}
