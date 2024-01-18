package com.inventorycontrol.service.impl;

import com.inventorycontrol.exception.DataAlreadyRegisteredException;
import com.inventorycontrol.model.UserModel;
import com.inventorycontrol.repository.UserRepository;
import com.inventorycontrol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new NoResultException("Usuário não encontrado."));
    }

    @Override
    public UserModel save(UserModel model) {
        if (userRepository.existsByLogin(model.getLogin())) {
            throw new DataAlreadyRegisteredException("Já existe um usuário com o LOGIN informado.");
        }
        if (userRepository.existsByEmail(model.getEmail())) {
            throw new DataAlreadyRegisteredException("Já existe um usuário com o EMAIL informado.");
        }
        if (userRepository.existsByCpf(model.getCpf())) {
            throw new DataAlreadyRegisteredException("Já existe um usuário com o CPF informado.");
        }

        String encodePassword = passwordEncoder.encode(model.getPassword());
        model.setPassword(encodePassword);

        return userRepository.save(model);
    }

    @Override
    public UserModel update(UUID uuid, UserModel model) {
        var response = userRepository.findById(uuid).orElseThrow(() -> new NoResultException("Usuário não encontrado."));
        model.setUserId(uuid);
        if (model.getPassword().equals(response.getPassword())){
            throw new DataAlreadyRegisteredException("Senha já está em uso.");
        }

        String encodePassword = passwordEncoder.encode(model.getPassword());
        model.setPassword(encodePassword);

        userRepository.save(model);
        return model;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = userRepository.findById(uuid).orElseThrow(() -> new NoResultException("Usuário não encontrado."));
        userRepository.delete(response);
        return uuid;
    }
}
