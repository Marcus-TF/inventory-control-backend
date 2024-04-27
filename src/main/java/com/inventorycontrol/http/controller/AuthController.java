package com.inventorycontrol.http.controller;

import com.inventorycontrol.config.security.service.AuthService;
import com.inventorycontrol.http.dto.request.AuthenticationRequest;
import com.inventorycontrol.http.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticationResponse(request));
    }
}
