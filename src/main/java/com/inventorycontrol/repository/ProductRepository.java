package com.inventorycontrol.repository;

import com.inventorycontrol.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    boolean existsByProductName(String productName);
}
