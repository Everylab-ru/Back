package ru.webapp.everylab.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "Product Type Dto")
public record ProductTypeDto(

        @NotBlank
        @Schema(description = " Product Type name", example = "Лабораторная работа")
        String name
) {
}
