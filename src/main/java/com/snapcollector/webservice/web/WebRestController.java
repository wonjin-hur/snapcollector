package com.snapcollector.webservice.web;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@CrossOrigin(origins = "http://127.0.0.1:8080", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class WebRestController {

    private Environment env;

    @GetMapping("/profile")
    public String getProfile() {
        return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
    }

    @CrossOrigin
    @GetMapping("/vuejs")
    public String getVuejs() {
        return "Hello vuejs";
    }




}