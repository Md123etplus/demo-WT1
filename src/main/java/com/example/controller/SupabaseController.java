package com.example.controller;

import com.example.service.SupabaseService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class SupabaseController {
    private final SupabaseService supabaseService;

    public SupabaseController(SupabaseService supabaseService) {
        this.supabaseService = supabaseService;
    }

    // Get all users
    @GetMapping
    public Mono<String> getUsers() {
        return supabaseService.fetchUsers();
    }

    // Add a new user
    @PostMapping("/add")
    public Mono<String> addUser(@RequestParam String email, @RequestParam String name) {
        return supabaseService.addUser(email, name);
    }
}
