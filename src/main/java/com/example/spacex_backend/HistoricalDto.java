package com.example.spacex_backend;

public record HistoricalDto(String id, String title, String details, String event_date_utc, Links links) {
    public record Links(String article) {}
}