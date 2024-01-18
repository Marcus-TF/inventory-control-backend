package com.inventorycontrol.service;

import com.inventorycontrol.model.CategoryModel;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<CategoryModel> findAll();

    CategoryModel findById(UUID uuid);

    CategoryModel save(CategoryModel categoryModel);

    CategoryModel update(CategoryModel categoryModel, UUID uuid);

    UUID delete(UUID uuid);
}
