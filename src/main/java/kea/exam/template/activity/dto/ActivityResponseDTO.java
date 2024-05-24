package kea.exam.template.activity.dto;

public record ActivityResponseDTO(
        Long id,
        String name,
        Boolean isOpen,
        String type,
        Integer price
) {
}
