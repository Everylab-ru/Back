package ru.webapp.everylab.dto.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AuthenticationResponse(
        @JsonProperty("access_token")
        String accessToken
) {
}
