package com.inventorycontrol.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInsertResponse {

    private UUID userId;

    private String login;

    private String email;

    private String password;

    private String cpf;

    private List<ProfileResponse> profileResponseList;
}
