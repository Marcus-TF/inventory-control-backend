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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "provider", schema = "inventory")
public class ProviderModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID providerId;

    private String providerName;

    private String address;

    private String num;

    private String district;

    private String cep;

    private String contact;

    private String cnpj;

    private String insc;

    private String telephone;

    @ManyToMany
    @JoinTable(
            name = "provider_cities",
            schema = "inventory",
            joinColumns = @JoinColumn(name = "providerId_fk"),
            inverseJoinColumns = @JoinColumn(name = "cityId_fk"))
    private List<CityModel> cityModelList;
}
