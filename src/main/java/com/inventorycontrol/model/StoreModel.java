package com.inventorycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Store", schema = "inventory")
public class StoreModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID storeId;

    private String name;

    private String zipCode;

    private String address;

    private Integer number;

    private String district;

    private String telephone;

    private String subscription;

    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private CityModel cityModel;
}
