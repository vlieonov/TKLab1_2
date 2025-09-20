package com.lieonov.lab1.controller;

import com.lieonov.lab1.parser.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private Parser parser;

    @PostMapping("/link")
    public Map<String, String> link(@RequestBody Map<String, String> payload) throws IOException {
        HashMap<String, String> response = new HashMap<>();
        response.put("text", parser.runParser(Integer.parseInt(payload.get("limit")), payload.get("link")));
        return response;
    }
}
