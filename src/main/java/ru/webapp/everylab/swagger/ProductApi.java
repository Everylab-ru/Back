package ru.webapp.everylab.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import ru.webapp.everylab.dto.product.ProductRequest;
import ru.webapp.everylab.dto.product.ProductResponse;
import ru.webapp.everylab.dto.product.ProductTypeDto;

import java.util.List;

@Tag(name = "Product controller", description = "Product API")
public interface ProductApi {

    @Operation(summary = "Add new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New product created successfully",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))),
    })
    ProductResponse addProduct(
            @RequestBody @Valid ProductRequest productRequest,
            HttpServletRequest request
    );

    @Operation(summary = "Get all products by type",
            description = "Returns a list of products filtered by a specified type.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products")
    })
    List<ProductResponse> getAllProductsByType(String type);

    @Operation(summary = "Get all product types", description = "Returns a list of all available product types.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of product types")
    })
    List<ProductTypeDto> getAllProductTypes();
}
