package com.inventorycontrol.service;

import com.inventorycontrol.model.ProfileModel;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    List<ProfileModel> findAll();

    ProfileModel findById(UUID uuid);

    ProfileModel save(ProfileModel profileModel);

    ProfileModel update(UUID uuid, ProfileModel profileModel);

    UUID delete(UUID uuid);
}
