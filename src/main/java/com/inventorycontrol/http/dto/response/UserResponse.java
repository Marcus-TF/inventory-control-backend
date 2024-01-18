package com.inventorycontrol.http.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Schema(name = "UserResponse", description = "Representa os dados de retorno ao cadastrar um usuário.")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID userId;

    @NotBlank(message = "{user.required.login}")
    @JsonProperty("login")
    @Schema(description = "Login de acesso")
    private String login;

    @NotBlank(message = "{user.required.email}")
    @JsonProperty("email")
    @Schema(description = "Email de acesso")
    private String email;

    @NotBlank(message = "{user.required.password}")
    @JsonProperty("password")
    @Schema(description = "Senha de acesso")
    private String password;

    @NotBlank(message = "{user.required.cpf}")
    @JsonProperty("cpf")
    @Schema(description = "CPF do usuário")
    private String cpf;

    private List<ProfileResponse> profileResponseList;
}
