package com.inventorycontrol.service.impl;

import com.inventorycontrol.exception.DataAlreadyRegisteredException;
import com.inventorycontrol.model.ProductModel;
import com.inventorycontrol.repository.ProductRepository;
import com.inventorycontrol.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel findById(UUID uuid) {
        return productRepository.findById(uuid).orElseThrow(() -> new NoResultException("Produto não encontrado."));
    }

    @Override
    public ProductModel save(ProductModel productModel) {
        if (productRepository.existsByProductName(productModel.getProductName())) {
            throw new DataAlreadyRegisteredException("Produto já armazenado no sistema.");
        }
        return productRepository.save(productModel);
    }

    @Override
    public ProductModel update(UUID uuid, ProductModel productModel) {
        productRepository.findById(uuid).orElseThrow(() -> new NoResultException("Produto não encontrado."));
        productModel.setProductId(uuid);
        productRepository.save(productModel);
        return productModel;
    }

    @Override
    public UUID delete(UUID uuid) {
        var response = productRepository.findById(uuid).orElseThrow(() -> new NoResultException("Produto não encontrado."));
        productRepository.delete(response);
        return uuid;
    }
}
