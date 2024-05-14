package kea.exam.template.booking;

import kea.exam.template.activity.dto.ActivityResponseDTO;
import kea.exam.template.product.dto.ProductResponseDTO;
import kea.exam.template.user.User;
import kea.exam.template.user.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public record BookingResponseDTO(
        Long id,
        double price,
        String start,
        String end,
        UserDTO user,
        ActivityResponseDTO activity,
        List<String> participants,
        List<ProductResponseDTO> products

) {
}
