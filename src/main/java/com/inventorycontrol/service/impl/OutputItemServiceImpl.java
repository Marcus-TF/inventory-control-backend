package com.inventorycontrol.service.impl;

import com.inventorycontrol.model.OutputItemModel;
import com.inventorycontrol.repository.OutputItemRepository;
import com.inventorycontrol.service.OutputItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OutputItemServiceImpl implements OutputItemService {

    private final OutputItemRepository itemRepository;

    @Override
    public List<OutputItemModel> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public OutputItemModel findById(UUID uuid) {
        return itemRepository.findById(uuid).orElseThrow(() -> new NoResultException("Item de saída não encontrado."));
    }

    @Override
    public OutputItemModel save(OutputItemModel entryItemModel) {
        return itemRepository.save(entryItemModel);
    }

    @Override
    public OutputItemModel update(UUID uuid, OutputItemModel outputItemModel) {
        itemRepository.findById(uuid).orElseThrow(() -> new NoResultException("Item de saída não encontrado."));
        outputItemModel.setOutputItemId(uuid);
        itemRepository.save(outputItemModel);
        return outputItemModel;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = itemRepository.findById(uuid).orElseThrow(() -> new NoResultException("Item de saída não encontrado."));
        itemRepository.delete(response);
        return uuid;
    }
}
