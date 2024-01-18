package com.inventorycontrol.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProductRequest {

    @NotBlank(message = "Informe o nome do produto.")
    private String productName;

    @NotBlank(message = "Informe o peso.")
    private String weight;

    @NotBlank(message = "Informe se é controlado.")
    private boolean controlled;

    @NotBlank(message = "Informe a quantia mínima.")
    private Integer minimumQuantity;

    @NotBlank(message = "Informe o ID da Categoria.")
    private UUID categoryId;

    @NotBlank(message = "Informe o ID do Fornecedor.")
    private UUID providerId;
}
