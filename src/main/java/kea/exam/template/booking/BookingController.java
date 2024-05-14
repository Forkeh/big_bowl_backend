package kea.exam.template.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bookings")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping
    public ResponseEntity<List<BookingResponseDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable String id) {

        return ResponseEntity.ok(bookingService.getBookingsByUserId(id));

    }

}
