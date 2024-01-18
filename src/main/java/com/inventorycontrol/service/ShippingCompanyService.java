package com.inventorycontrol.service;

import com.inventorycontrol.model.ShippingCompanyModel;

import java.util.List;
import java.util.UUID;

public interface ShippingCompanyService {

    List<ShippingCompanyModel> findAll();

    ShippingCompanyModel findById(UUID uuid);

    ShippingCompanyModel save(ShippingCompanyModel shippingCompanyModel);

    ShippingCompanyModel update(UUID uuid, ShippingCompanyModel shippingCompanyModel);

    UUID delete(UUID uuid);
}
