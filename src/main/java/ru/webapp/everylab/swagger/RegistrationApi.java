package ru.webapp.everylab.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import ru.webapp.everylab.dto.authentication.AuthenticationResponse;
import ru.webapp.everylab.dto.user.UserRequest;

@Tag(name = "Registration controller", description = "Registration API")
public interface RegistrationApi {

    @Operation(summary = "Register user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
    })
    AuthenticationResponse register(@RequestBody @Valid UserRequest request, HttpServletResponse response);
}
