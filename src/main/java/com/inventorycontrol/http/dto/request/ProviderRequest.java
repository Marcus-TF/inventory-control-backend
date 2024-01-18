package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ProviderRequest {

    @NotBlank(message = "Informe o nome do Fornecedor.")
    private String providerName;

    @NotBlank(message = "Informe o endereço.")
    private String address;

    @NotBlank(message = "Informe o número.")
    private String num;

    @NotBlank(message = "Informe o bairro.")
    private String district;

    @NotBlank(message = "Informe o cep.")
    private String cep;

    @NotBlank(message = "Informe o nome do contato.")
    private String contact;

    @CNPJ
    @NotBlank(message = "Informe o CNPJ.")
    private String cnpj;

    @NotBlank(message = "Informe o número de inscrição.")
    private String insc;

    @NotBlank(message = "Informe o telefone.")
    private String telephone;

    @NotBlank(message = "Informe o/os IDs das cidades.")
    private List<UUID> cityListIds;
}
