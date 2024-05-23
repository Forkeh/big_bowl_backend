package kea.exam.template.booking.dto;

import jakarta.validation.constraints.*;


import java.time.LocalDateTime;
import java.util.List;

public record BookingRequestDTO(
        @FutureOrPresent
        String start,
        @NotNull
        Long activityId,
        @NotNull
        String userId,
        @NotNull
        Long duration,
        @NotEmpty
        List<String> participants,
        List<IProductRequest> products
) {
}




