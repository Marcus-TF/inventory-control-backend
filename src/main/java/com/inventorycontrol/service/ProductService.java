package com.inventorycontrol.service;

import com.inventorycontrol.model.ProductModel;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductModel> findAll();

    ProductModel findById(UUID uuid);

    ProductModel save(ProductModel productModel);

    ProductModel update(UUID uuid, ProductModel productModel);

    UUID delete(UUID uuid);
}
