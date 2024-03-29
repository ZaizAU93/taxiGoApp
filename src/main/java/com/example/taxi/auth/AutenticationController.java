package com.example.taxi.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AutenticationController {
    @Autowired
    private AuthentificateService service;

    @PostMapping("/register")
    public ResponseEntity<AuthentificcationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificcationResponse> authentificate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authentificate(request));
    }
}
