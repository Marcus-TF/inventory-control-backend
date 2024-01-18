package com.inventorycontrol.service;

import com.inventorycontrol.model.EntryItemModel;

import java.util.List;
import java.util.UUID;

public interface EntryItemService {
    List<EntryItemModel> findAll();

    EntryItemModel findById(UUID uuid);

    EntryItemModel save(EntryItemModel entryItemModel);

    EntryItemModel update(UUID uuid, EntryItemModel entryItemModel);

    UUID delete(UUID uuid);

}
