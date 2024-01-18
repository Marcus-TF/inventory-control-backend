package com.inventorycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipping_company", schema = "inventory")
public class ShippingCompanyModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID shippingCompanyId;

    private String name;

    private String address;

    private Integer number;

    private String district;

    private String zipCode;

    private String cnpj;

    private String subscription;

    private String contact;

    private String telephone;

    @ManyToMany
    @JoinTable(
            name = "shipping_company_cities",
            schema = "inventory",
            joinColumns = @JoinColumn(name = "shippingCompanyId_fk"),
            inverseJoinColumns = @JoinColumn(name = "cityId_fk"))
    private List<CityModel> cityModelList;
}
