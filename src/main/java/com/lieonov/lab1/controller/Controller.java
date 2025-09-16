package com.lieonov.lab1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {
    @PostMapping("/link")
    public String link(@RequestBody Map<String, String> payload) {
        String link = payload.get("link");
        return link;
    }
}
