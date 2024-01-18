package com.inventorycontrol.repository;

import com.inventorycontrol.model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<CityModel, UUID> {

    boolean existsByCityName(String cityName);
}
