package ru.webapp.everylab.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
@Schema(description = "Product Type Dto")
public record ProductTypeDto(

        @NotNull
        @Positive
        @Schema(description = "Product type id", example = "1")
        Integer id,

        @NotBlank
        @Schema(description = " Product Type name", example = "Лабораторная работа")
        String name
) {
}
