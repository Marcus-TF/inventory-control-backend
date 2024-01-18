package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class OutputItemRequest {

    @NotBlank(message = "Informe o lote.")
    private String batch;

    @NotBlank(message = "Informe a quantia.")
    private Integer amount;

    @NotBlank(message = "Informe o valor.")
    private Double value;

    @NotBlank(message = "Informe o ID da Saída.")
    private UUID exitId;

    @NotBlank(message = "Informe o ID do Produto.")
    private UUID productId;
}
