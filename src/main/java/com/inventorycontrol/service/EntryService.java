package com.inventorycontrol.service;

import com.inventorycontrol.model.EntryModel;

import java.util.List;
import java.util.UUID;

public interface EntryService {

    List<EntryModel> findAll();

    EntryModel findById(UUID uuid);

    EntryModel save(EntryModel model);

    EntryModel update(UUID uuid, EntryModel model);

    UUID delete(UUID uuid);
}
