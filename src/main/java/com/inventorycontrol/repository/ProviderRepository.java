package com.inventorycontrol.repository;

import com.inventorycontrol.model.ProviderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProviderRepository extends JpaRepository<ProviderModel, UUID> {

    boolean existsByProviderName(String name);
}
