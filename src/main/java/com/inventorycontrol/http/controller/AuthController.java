package com.inventorycontrol.http.controller;

import com.inventorycontrol.config.security.service.AuthService;
import com.inventorycontrol.http.dto.request.AuthenticationRequest;
import com.inventorycontrol.http.dto.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticationResponse(request));
    }
}
