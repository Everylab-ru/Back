package ru.webapp.everylab.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import ru.webapp.everylab.dto.authentication.AuthenticationRequest;
import ru.webapp.everylab.dto.authentication.AuthenticationResponse;
import ru.webapp.everylab.dto.user.UserRequest;

import java.io.IOException;

@Tag(name = "Authentication controller", description = "Authentication API")
public interface AuthenticationApi {

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
    })
    AuthenticationResponse register(@RequestBody @Valid UserRequest request, HttpServletResponse response);

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class)))
    })
    AuthenticationResponse authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest, HttpServletResponse response);

    @Operation(summary = "Refresh Tokens")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully")
    })
    AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
