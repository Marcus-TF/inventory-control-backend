package com.inventorycontrol.service.impl;

import com.inventorycontrol.model.ShippingCompanyModel;
import com.inventorycontrol.repository.ShippingCompanyRepository;
import com.inventorycontrol.service.ShippingCompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ShippingCompanyServiceImpl implements ShippingCompanyService {

    private final ShippingCompanyRepository shippingCompanyRepository;

    public List<ShippingCompanyModel> findAll() {
        return shippingCompanyRepository.findAll();
    }

    public ShippingCompanyModel findById(UUID uuid) {
        return shippingCompanyRepository.findById(uuid).orElseThrow(() -> new NoResultException("Transportadora não encontrada."));
    }

    public ShippingCompanyModel save(ShippingCompanyModel shippingCompanyModel) {
        return shippingCompanyRepository.save(shippingCompanyModel);
    }

    public ShippingCompanyModel update(UUID uuid, ShippingCompanyModel model) {
        shippingCompanyRepository.findById(uuid).orElseThrow(() -> new NoResultException("Transportadora não encontrada."));
        model.setShippingCompanyId(uuid);
        shippingCompanyRepository.save(model);
        return model;
    }

    public UUID delete(UUID uuid) {
        var response = shippingCompanyRepository.findById(uuid).orElseThrow(() -> new NoResultException("Transportadora não encontrada."));
        shippingCompanyRepository.delete(response);
        return uuid;
    }
}
