package com.inventorycontrol.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private UUID storeId;

    private String name;

    private String zipCode;

    private String address;

    private Integer number;

    private String district;

    private String telephone;

    private String subscription;

    private String cnpj;
}
