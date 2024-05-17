package kea.exam.template.activity.dto;

public record ActivityResponseDTO(
        Long id,
        String name,
        boolean isOpen,
        String type,
        Integer price
) {
}
