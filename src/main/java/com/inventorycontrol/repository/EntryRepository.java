package com.inventorycontrol.repository;

import com.inventorycontrol.model.EntryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntryRepository extends JpaRepository<EntryModel, UUID> {
}
