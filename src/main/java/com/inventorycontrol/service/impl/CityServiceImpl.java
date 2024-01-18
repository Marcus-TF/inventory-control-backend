package com.inventorycontrol.service.impl;

import com.inventorycontrol.exception.DataAlreadyRegisteredException;
import com.inventorycontrol.model.CityModel;
import com.inventorycontrol.repository.CityRepository;
import com.inventorycontrol.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<CityModel> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public CityModel findById(UUID uuid) {
        return cityRepository.findById(uuid).orElseThrow(() -> new NoResultException("Cidade não encontrada no sistema."));
    }

    @Override
    public CityModel save(CityModel cityModel) {
        if (cityRepository.existsByCityName(cityModel.getCityName())) {
            throw new DataAlreadyRegisteredException("Cidade já armazenada no sistema.");
        }
        return cityRepository.save(cityModel);
    }

    @Override
    public CityModel update(CityModel cityModel, UUID uuid) {
        cityRepository.findById(uuid).orElseThrow(() -> new NoResultException("Cidade não encontrada."));
        cityModel.setCityId(uuid);
        cityRepository.save(cityModel);
        return cityModel;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = cityRepository.findById(uuid).orElseThrow(() -> new NoResultException("Cidade não encontrada."));
        cityRepository.delete(response);
        return uuid;
    }
}
