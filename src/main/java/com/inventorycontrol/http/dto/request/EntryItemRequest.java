package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class EntryItemRequest {

    @NotBlank(message = "Informe o lote.")
    private String batch;

    @NotBlank(message = "Informe a quantia.")
    private Integer amount;

    @NotNull(message = "Informe o valor.")
    private Double value;

    @NotBlank(message = "Informe o ID da Entrada.")
    private UUID entryId;

    @NotBlank(message = "Informe o ID do Produto.")
    private UUID productId;
}
