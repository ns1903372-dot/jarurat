package com.ns1903372dot.whatsapp.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "application", "WhatsApp Chatbot Backend Simulation",
                "status", "running",
                "availableEndpoints", new String[]{"/health", "/webhook"},
                "usage", "Send a POST request to /webhook with JSON: {\"from\":\"919999999999\",\"message\":\"Hi\"}"
        );
    }
}
