package com.inventorycontrol.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingCompanyResponse {

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

    private List<CityResponse> cityResponseList;
}
