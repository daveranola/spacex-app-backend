package com.example.spacex_backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

//initializes http client, so we can resue the url easily
@Configuration
public class HttpClientsConfig  {
    @Bean
    RestClient spacexClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://api.spacexdata.com/v4")
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE) //header is accept, which retrieves a json
                .build();
    }
}
