package com.example.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SupabaseService {
    private final WebClient webClient;

    public SupabaseService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://aqfwsgieequeqdcwneff.supabase.co/rest/v1") // Supabase URL
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("apikey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...") // API Key
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
                .build();
    }

    // Fetch all users from Supabase
    public Mono<String> fetchUsers() {
        return webClient.get()
                .uri("/users")  // Supabase table name
                .retrieve()
                .bodyToMono(String.class);
    }

    // Insert a new user into Supabase
    public Mono<String> addUser(String email, String name) {
        String jsonBody = String.format("{\"email\": \"%s\", \"name\": \"%s\"}", email, name);

        return webClient.post()
                .uri("/users")
                .bodyValue(jsonBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}
