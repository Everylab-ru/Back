package ru.webapp.everylab.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@Schema(description = "Product Response Dto")
public record ProductResponse(
        @Schema(description = "Product header", example = "Lab1")
        String header,

        @Schema(description = "Product description")
        String description,

        @Schema(description = "Product price", example = "15.90")
        BigDecimal price,

        @Schema(description = "productTypeId", example = "1")
        Integer productTypeId
) {
}
