package com.inventorycontrol.service;

import com.inventorycontrol.model.OutputItemModel;

import java.util.List;
import java.util.UUID;

public interface OutputItemService {

    List<OutputItemModel> findAll();

    OutputItemModel findById(UUID uuid);

    OutputItemModel save(OutputItemModel entryItemModel);

    OutputItemModel update(UUID uuid, OutputItemModel outputItemModel);

    UUID delete(UUID uuid);
}
