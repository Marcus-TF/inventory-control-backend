package com.inventorycontrol.service;

import com.inventorycontrol.model.StoreModel;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    List<StoreModel> findAll();

    StoreModel findById(UUID uuid);

    StoreModel save(StoreModel model);

    StoreModel update(UUID uuid, StoreModel model);

    UUID delete(UUID uuid);
}
