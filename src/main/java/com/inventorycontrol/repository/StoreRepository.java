package com.inventorycontrol.repository;

import com.inventorycontrol.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<StoreModel, UUID> {
}
