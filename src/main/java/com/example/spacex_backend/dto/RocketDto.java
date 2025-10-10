package com.example.spacex_backend.dto;

import java.util.List;

public record RocketDto(String id, String name, Height height,
                        Diameter diameter, Mass mass,
                        List<String> flickr_images,
                        boolean active,
                        String description,
                        long cost_per_launch) {
    public record Height(double meters, double feet) {}
    public record Diameter(double meters, double feet) {}
    public record Mass(double kg, double lb) {}
}
