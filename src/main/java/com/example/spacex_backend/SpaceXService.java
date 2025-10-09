package com.example.spacex_backend;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient; //allows us to call api

import java.util.List;

@Service
public class SpaceXService {
    private final RestClient spaceXClient;

    public SpaceXService(RestClient spaceXClient) {
        this.spaceXClient = spaceXClient;
    } //construct the spaceXClient



    //.body(new ParameterizedTypeReference<List<HistoricalDto>>() {}); //lets java know its meant to be of type HistoricalDto array


    public List<RocketDto> getAllRockets() {
        return spaceXClient.get()
                .uri("/rockets")
                .retrieve()
                .body(new ParameterizedTypeReference<List<RocketDto>>() {});
    }
}
