package com.inventorycontrol.service.impl;

import com.inventorycontrol.model.ExitModel;
import com.inventorycontrol.repository.ExitRepository;
import com.inventorycontrol.service.ExitService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExitServiceImpl implements ExitService {

    private final ExitRepository exitRepository;

    @Override
    public List<ExitModel> findAll() {
        return exitRepository.findAll();
    }

    @Override
    public ExitModel findById(UUID uuid) {
        return exitRepository.findById(uuid).orElseThrow(() -> new NoResultException("Sáida não encontrada."));
    }

    @Override
    public ExitModel save(ExitModel model) {
        return exitRepository.save(model);
    }

    @Override
    public ExitModel update(UUID uuid, ExitModel model) {
        exitRepository.findById(uuid).orElseThrow(() -> new NoResultException("Sáida não encontrada."));
        model.setExitId(uuid);
        exitRepository.save(model);
        return model;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = exitRepository.findById(uuid).orElseThrow(() -> new NoResultException("Sáida não encontrada."));
        exitRepository.delete(response);
        return uuid;
    }
}
