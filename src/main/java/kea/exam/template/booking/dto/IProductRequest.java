package kea.exam.template.booking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record IProductRequest(
        @NotNull
        Long id,
        @Min(1)
        int quantity
) {
}
