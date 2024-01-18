package com.inventorycontrol.service.impl;

import com.inventorycontrol.model.StoreModel;
import com.inventorycontrol.repository.StoreRepository;
import com.inventorycontrol.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public List<StoreModel> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public StoreModel findById(UUID uuid) {
        return storeRepository.findById(uuid).orElseThrow(() -> new NoResultException("Loja não encontrada."));
    }

    @Override
    public StoreModel save(StoreModel model) {
        return storeRepository.save(model);
    }

    @Override
    public StoreModel update(UUID uuid, StoreModel model) {
        storeRepository.findById(uuid).orElseThrow(() -> new NoResultException("Loja não encontrada."));
        model.setStoreId(uuid);
        storeRepository.save(model);
        return model;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = storeRepository.findById(uuid).orElseThrow(() -> new NoResultException("Loja não encontrada."));
        storeRepository.delete(response);
        return uuid;
    }
}
