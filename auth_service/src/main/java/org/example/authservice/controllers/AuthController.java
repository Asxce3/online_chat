package org.example.authservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.authservice.models.Candidate;
import org.example.authservice.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping()
public class AuthController {
    @Autowired
    Service service;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void createToken(@RequestBody Candidate candidate, HttpServletResponse response, HttpServletRequest request) {
        Candidate user = service.checkUser(candidate);
        HashMap<String, String> tokens = service.createTokens(user);
        response.addHeader("accessToken", tokens.get("accessToken"));
        response.addHeader("refreshToken", tokens.get("refreshToken"));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void verifyToken(@RequestHeader("accessToken") String token) {
        service.verifyAccessToken(token);
    }

    @GetMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public void refreshToken(@RequestHeader(value = "refreshToken") String refreshToken, HttpServletResponse response) {
        Candidate user = service.getUserByRefreshToken(refreshToken);
        HashMap<String, String> tokens = service.createTokens(user);
        response.addHeader("accessToken", tokens.get("accessToken"));
        response.addHeader("refreshToken", tokens.get("refreshToken"));
    }
}
