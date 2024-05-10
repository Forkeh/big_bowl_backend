package kea.exam.template.product.dto;

public record ProductResponseDTO(
        Long id,
        String name,
        String image,
        double price,
        int stock,
        String category
) {
}
