package com.inventorycontrol.config.security.service;

import com.inventorycontrol.http.dto.request.AuthenticationRequest;
import com.inventorycontrol.http.dto.response.AuthenticationResponse;
import com.inventorycontrol.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticationResponse(AuthenticationRequest request) {
        var user = userRepository.findByLogin(request.getLogin()).orElseThrow(() -> new NoResultException("Usuário não encontrado."));

        var jwtToken = jwtService.generateTokenUser(user);
        var token = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        return token;
    }
}
