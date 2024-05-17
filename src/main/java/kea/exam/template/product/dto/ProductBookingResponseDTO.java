package kea.exam.template.product.dto;

public record ProductBookingResponseDTO(
        Long id,
        String name,
        String image,

        int quantity
) {
}
