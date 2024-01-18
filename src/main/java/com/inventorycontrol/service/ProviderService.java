package com.inventorycontrol.service;

import com.inventorycontrol.model.ProviderModel;

import java.util.List;
import java.util.UUID;

public interface ProviderService {

    List<ProviderModel> findAll();

    ProviderModel findById(UUID uuid);

    ProviderModel save(ProviderModel providerModel);

    ProviderModel update(UUID uuid, ProviderModel providerModel);

    UUID delete(UUID uuid);
}
