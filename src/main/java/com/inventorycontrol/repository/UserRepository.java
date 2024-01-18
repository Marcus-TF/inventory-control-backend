package com.inventorycontrol.repository;

import com.inventorycontrol.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<UserModel> findByLogin(String login);
}
