package com.projeto.pessoal.data.v1.AuthenticationDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"email", "access_token" })
public class CredentialsResponseDTO extends RepresentationModel<CredentialsResponseDTO> {
    private String email;
    @JsonProperty("access_token")
    private String accessToken;
}
