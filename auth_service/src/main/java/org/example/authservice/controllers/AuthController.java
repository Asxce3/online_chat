package org.example.authservice.controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
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
    public void createToken(@RequestBody Candidate candidate, HttpServletResponse response) {
        Candidate user = service.checkUser(candidate);
        HashMap<String, String> tokens = service.createTokens(user);
        response.addHeader("accessToken", tokens.get("accessToken"));
        response.addHeader("refreshToken", tokens.get("refreshToken"));

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public void verifyToken(@RequestHeader("accessToken") String token, HttpServletResponse response) {
        DecodedJWT decodedJWT = service.verifyAccessToken(token);
        String username = decodedJWT.getClaim("username").asString();
        response.addHeader("username", username);
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
