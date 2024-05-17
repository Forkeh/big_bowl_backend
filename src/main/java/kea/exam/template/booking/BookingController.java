package kea.exam.template.booking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @GetMapping("users/{id}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUserId(@PathVariable String id) {

        return ResponseEntity.ok(bookingService.getBookingsByUserId(id));

    }

    @PatchMapping("{id}/participants")
    public ResponseEntity<BookingResponseDTO> updateBookingParticipants(@PathVariable Long id, @RequestBody List<String> participantNames) {
        return ResponseEntity.ok(bookingService.updateBookingParticipants(id, participantNames));
    }

}
