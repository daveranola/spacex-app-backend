package com.example.spacex_backend;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class SpaceXService {
    private final RestClient spaceXClient;

    public SpaceXService(RestClient spaceXClient) {
        this.spaceXClient = spaceXClient;
    } //construct the spaceXClient

    public LaunchDto getLaunch(String id) {
        return spaceXClient.get()
                .uri("/launches/{id}", id)
                .retrieve()
                .body(LaunchDto.class);
    }

    public List<LaunchDto> listUpcoming() {
        return spaceXClient.get()
                .uri("/launches/upcoming")
                .retrieve()
                .body(new ParameterizedTypeReference<List<LaunchDto>>() {});
    }

    public RocketDto getRocket(String id) {
        return spaceXClient.get()
                .uri("/rockets/{id}", id)
                .retrieve()
                .body(RocketDto.class);
    }
}
