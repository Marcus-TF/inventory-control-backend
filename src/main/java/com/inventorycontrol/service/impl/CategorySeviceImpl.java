package com.inventorycontrol.service.impl;

import com.inventorycontrol.exception.DataAlreadyRegisteredException;
import com.inventorycontrol.model.CategoryModel;
import com.inventorycontrol.repository.CategoryRepository;
import com.inventorycontrol.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategorySeviceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public CategoryModel findById(UUID uuid) {
        return categoryRepository.findById(uuid).orElseThrow(() -> new NoResultException("Categoria não encontrada."));
    }

    @Override
    public CategoryModel save(CategoryModel categoryModel) {
        if (categoryRepository.existsByCategoryName(categoryModel.getCategoryName())){
            throw new DataAlreadyRegisteredException("Categoria já armazenada no sistema.");
        }
        return categoryRepository.save(categoryModel);
    }

    @Override
    public CategoryModel update(CategoryModel categoryModel, UUID uuid) {
        categoryRepository.findById(uuid).orElseThrow(() -> new NoResultException("Categoria não encontrada."));
        categoryModel.setCategoryId(uuid);
        categoryRepository.save(categoryModel);
        return categoryModel;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = categoryRepository.findById(uuid).orElseThrow(() -> new NoResultException("Categoria não encontrada."));
        categoryRepository.delete(response);
        return uuid;
    }
}
