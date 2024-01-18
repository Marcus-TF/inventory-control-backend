package com.inventorycontrol.repository;

import com.inventorycontrol.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {

    boolean existsByCategoryName(String categoryName);
}
