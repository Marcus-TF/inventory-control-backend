package com.inventorycontrol.service.impl;

import com.inventorycontrol.model.EntryModel;
import com.inventorycontrol.repository.EntryRepository;
import com.inventorycontrol.service.EntryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    @Override
    public List<EntryModel> findAll() {
        return entryRepository.findAll();
    }

    @Override
    public EntryModel findById(UUID uuid) {
        return entryRepository.findById(uuid).orElseThrow(() -> new NoResultException("Entrada não encontrada."));
    }

    @Override
    public EntryModel save(EntryModel model) {
        return entryRepository.save(model);
    }

    @Override
    public EntryModel update(UUID uuid, EntryModel model) {
        entryRepository.findById(uuid).orElseThrow(() -> new NoResultException("Entrada não encontrada."));
        model.setEntryId(uuid);
        entryRepository.save(model);
        return model;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = entryRepository.findById(uuid).orElseThrow(() -> new NoResultException("Entrada não encontrada."));
        entryRepository.delete(response);
        return uuid;
    }
}
