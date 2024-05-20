package kea.exam.template.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import kea.exam.template.product.dto.ProductBookingRequestDTO;
import kea.exam.template.product.dto.ProductBookingResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record BookingRequestDTO(
        @Min(1)
        double price,
        @FutureOrPresent
        LocalDateTime start,
        @Future
        LocalDateTime end,
        @NotNull
        String userId,
        @NotNull
        Long activityId,
        List<String> participants,
        List<ProductBookingRequestDTO> products

) {
}
