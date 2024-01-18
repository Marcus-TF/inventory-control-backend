package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
import java.util.UUID;

@Data
public class ShippingCompanyRequest {

    @NotBlank(message = "Informe o nome.")
    private String name;

    @NotBlank(message = "Informe o endereço.")
    private String address;

    @NotBlank(message = "Informe o número.")
    private Integer number;

    @NotBlank(message = "Informe o bairro.")
    private String district;

    @NotBlank(message = "Informe o cep.")
    private String zipCode;

    @CNPJ
    @NotBlank(message = "Informe o cnpj.")
    private String cnpj;

    @NotBlank(message = "Informe a inscrição.")
    private String subscription;

    @NotBlank(message = "Informe o nome do contato.")
    private String contact;

    @NotBlank(message = "Informe o telefone.")
    private String telephone;

    @NotBlank(message = "Informe o/os IDs das cidades.")
    private List<UUID> cityListIds;
}
