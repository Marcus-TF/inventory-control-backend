package com.inventorycontrol.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProviderResponse {

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

    private List<CityResponse> cityResponseList;
}
