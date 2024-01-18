package com.inventorycontrol.repository;

import com.inventorycontrol.model.ShippingCompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShippingCompanyRepository extends JpaRepository<ShippingCompanyModel, UUID> {
}
