package com.inventorycontrol.service;

import com.inventorycontrol.model.CityModel;

import java.util.List;
import java.util.UUID;

public interface CityService {

    List<CityModel> findAll();

    CityModel findById(UUID uuid);

    CityModel save(CityModel cityModel);

    CityModel update(CityModel cityModel, UUID uuid);

    UUID delete(UUID uuid);
}
