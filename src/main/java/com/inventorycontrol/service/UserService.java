package com.inventorycontrol.service;

import com.inventorycontrol.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserModel> findAll();

    UserModel findById(UUID uuid);

    UserModel save(UserModel model);

    UserModel update(UUID uuid, UserModel model);

    UUID delete(UUID uuid);
}
