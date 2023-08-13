package com.projeto.pessoal.data.v1.AuthenticationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsRequestDTO {
    private String username;
    private String password;
}
