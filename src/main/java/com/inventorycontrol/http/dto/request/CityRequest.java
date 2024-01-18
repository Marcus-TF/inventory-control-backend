package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityRequest {

    @NotBlank(message = "Informe o nome da cidade.")
    private String cityName;

    @NotBlank(message = "Informe unidade federativa.")
    private String uf;
}
