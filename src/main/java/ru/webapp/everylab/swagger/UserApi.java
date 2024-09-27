package ru.webapp.everylab.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import ru.webapp.everylab.dto.user.UserResponse;

@Tag(name = "User controller", description = "User API")
public interface UserApi {

    @Operation(summary = "Get information about user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieving user information was successful",
                    content = @Content(schema = @Schema(implementation = UserResponse.class))),
    })
    UserResponse getUser(HttpServletRequest request);
}
