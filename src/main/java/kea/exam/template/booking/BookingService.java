package kea.exam.template.booking;

import kea.exam.template.activity.ActivityService;
import kea.exam.template.participant.Participant;
import kea.exam.template.product.ProductService;
import kea.exam.template.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {


    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ActivityService activityService;
    private final ProductService productService;


    public BookingService(BookingRepository bookingRepository, UserService userService, ActivityService activityService, ProductService productService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.activityService = activityService;
        this.productService = productService;
    }

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream().map(this::toDTO).toList();
    }


    public BookingResponseDTO toDTO(Booking entity) {
        return new BookingResponseDTO(
                entity.getId(),
                entity.getTotalPrice(),
                formatDate(entity.getStartTime()),
                formatDate(entity.getEndTime()),
                userService.toDTO(entity.getUser()),
                activityService.toDTO(entity.getActivity()),
                entity.getParticipants().stream().map(Participant::getName).toList(),
                entity.getProducts().stream().map(productService::toDTO).toList()
        );
    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTime.format(formatter);
    }
}
