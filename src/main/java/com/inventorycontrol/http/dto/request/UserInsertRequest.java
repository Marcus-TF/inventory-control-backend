package com.inventorycontrol.http.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

@Data
public class UserInsertRequest {

    @NotBlank(message = "Informe o login.")
    private String login;

    @NotBlank(message = "Informe um email válido.")
    @Email
    private String email;

    @NotBlank(message = "Informe a senha.")
    @Size(min = 8, max = 15)
    private String password;

    @NotBlank(message = "Informe um cpf válido.")
    @CPF
    private String cpf;

    @NotBlank(message = "Informe o/os IDs dos perfis.")
    private List<UUID> profileIds;
}
