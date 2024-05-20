package kea.exam.template.booking.dto;

import kea.exam.template.activity.dto.ActivityResponseDTO;
import kea.exam.template.product.dto.ProductBookingResponseDTO;
import kea.exam.template.user.dto.UserDTO;

import java.util.List;

public record BookingResponseDTO(
        Long id,
        double price,
        String start,
        String end,
        UserDTO user,
        ActivityResponseDTO activity,
        List<String> participants,
        List<ProductBookingResponseDTO> products

) {
}
