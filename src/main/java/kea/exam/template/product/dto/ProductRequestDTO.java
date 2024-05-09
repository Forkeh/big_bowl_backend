package kea.exam.template.product.dto;

import jakarta.validation.constraints.*;

public record ProductRequestDTO(

        @NotNull
        String name,

        @NotNull
        String image,

        @Min(1)
        double price,

        @Min(1)
        int stock,

        @NotNull
        String category
) {
}
