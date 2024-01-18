package com.inventorycontrol.repository;

import com.inventorycontrol.model.EntryItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntryItemRepository extends JpaRepository<EntryItemModel, UUID> {
}
