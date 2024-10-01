package ru.webapp.everylab.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Schema(description = "Product Response Dto")
public record ProductResponse(
        @NotBlank
        @Schema(description = "Product header", example = "Lab1")
        String header,

        @NotBlank
        @Schema(description = "Product description")
        String description,

        @NotBlank
        @Digits(integer = 16, fraction = 2)
        @DecimalMin(value = "0.00")
        @Schema(description = "Product price", example = "15.90")
        BigDecimal price,

        /*@NotBlank
        ProductTypeDto productType*/

        @NotNull
        @Positive
        @Schema(description = "productTypeId", example = "1")
        Integer productTypeId
) {
}
