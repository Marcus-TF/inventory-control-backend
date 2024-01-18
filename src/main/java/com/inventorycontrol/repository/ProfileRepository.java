package com.inventorycontrol.repository;

import com.inventorycontrol.model.ProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<ProfileModel, UUID> {
}
