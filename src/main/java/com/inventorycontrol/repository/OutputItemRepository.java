package com.inventorycontrol.repository;

import com.inventorycontrol.model.OutputItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutputItemRepository extends JpaRepository<OutputItemModel, UUID> {
}
