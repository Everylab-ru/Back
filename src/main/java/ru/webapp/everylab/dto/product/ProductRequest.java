package ru.webapp.everylab.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Schema(description = "Product request Dto")
public record ProductRequest(
        @NotBlank
        @Schema(description = "Product header", example = "Lab1")
        String header,

        @NotBlank
        @Schema(description = "Product description")
        String description,

        @NotNull
        @Digits(integer = 16, fraction = 2)
        @DecimalMin(value = "0.00")
        @Schema(description = "Product price", example = "15.90")
        BigDecimal price,

        @NotNull
        @Positive
        @Schema(description = "productTypeId", example = "1")
        Integer productTypeId
) {
}
