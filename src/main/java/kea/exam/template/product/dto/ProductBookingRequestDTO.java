package kea.exam.template.product.dto;

import jakarta.validation.constraints.Min;

public record ProductBookingRequestDTO(
        Long id,
        @Min(1)
        int quantity
) {
}
