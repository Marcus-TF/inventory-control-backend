package com.inventorycontrol.service.impl;

import com.inventorycontrol.exception.DataAlreadyRegisteredException;
import com.inventorycontrol.model.ProviderModel;
import com.inventorycontrol.repository.ProviderRepository;
import com.inventorycontrol.service.ProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Override
    public List<ProviderModel> findAll() {
        return providerRepository.findAll();
    }

    @Override
    public ProviderModel findById(UUID uuid) {
        return providerRepository.findById(uuid).orElseThrow(() -> new NoResultException("Fornecedor não armazenado no sistema."));
    }

    @Override
    public ProviderModel save(ProviderModel providerModel) {
        if (providerRepository.existsByProviderName(providerModel.getProviderName())) {
            throw new DataAlreadyRegisteredException("Fornecedor já armazenado no sistema.");
        }
        return providerRepository.save(providerModel);
    }

    @Override
    public ProviderModel update(UUID uuid, ProviderModel providerModel) {
        providerRepository.findById(uuid).orElseThrow(() -> new NoResultException("Fornecedor não armazenado no sistema."));
        providerModel.setProviderId(uuid);
        providerRepository.save(providerModel);
        return providerModel;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = providerRepository.findById(uuid).orElseThrow(() -> new NoResultException("Fornecedor não armazenado no sistema."));
        providerRepository.delete(response);
        return uuid;
    }
}
