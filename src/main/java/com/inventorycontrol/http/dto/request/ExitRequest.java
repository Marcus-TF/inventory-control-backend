package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class ExitRequest {

    @NotBlank(message = "Informe o valor total.")
    private Double total;

    @NotBlank(message = "Informe o envio.")
    private Double shipping;

    @NotBlank(message = "Informe o imposto.")
    private Double tax;

    @NotBlank(message = "Informe o ID da loja.")
    private UUID storeId;

    @NotBlank(message = "Informe o ID da transportadora.")
    private UUID shippingCompanyId;
}
