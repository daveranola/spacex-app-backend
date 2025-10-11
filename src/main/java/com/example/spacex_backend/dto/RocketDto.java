package com.example.spacex_backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record RocketDto(
        String id,
        String name,
        String type,
        boolean active,
        int stages,
        int boosters,
        Height height,
        Diameter diameter,
        Mass mass,
        Engines engines,
        FirstStage firstStage,
        SecondStage secondStage,
        List<String> flickrImages,
        String description,
        String company,
        String country,
        String wikipedia,
        String firstFlight,
        long costPerLaunch,
        int successRatePct
) {
    public record Height(double meters, double feet) {}
    public record Diameter(double meters, double feet) {}
    public record Mass(double kg, double lb) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Engines(
            Isp isp,
            int number,
            String type,
            String version,
            String layout,
            Integer engineLossMax,
            String propellant1,
            String propellant2,
            double thrustToWeight
    ) {
        public record Isp(Integer seaLevel, Integer vacuum) {}
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record FirstStage(
            Integer engines
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SecondStage(
            Integer engines
    ) {}
}
