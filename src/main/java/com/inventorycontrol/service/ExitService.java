package com.inventorycontrol.service;

import com.inventorycontrol.model.ExitModel;

import java.util.List;
import java.util.UUID;

public interface ExitService {

    List<ExitModel> findAll();

    ExitModel findById(UUID uuid);

    ExitModel save(ExitModel model);

    ExitModel update(UUID uuid, ExitModel model);

    UUID delete(UUID uuid);
}
