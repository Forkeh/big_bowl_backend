package kea.exam.template.booking;

import kea.exam.template.booking.dto.BookingOccupiedTimesResponseDTO;
import kea.exam.template.booking.dto.BookingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @DeleteMapping("{id}")
    public ResponseEntity<BookingResponseDTO> deleteBookingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookingService.deleteBookingById(id));
    }


    @GetMapping("times")
    public ResponseEntity<List<BookingOccupiedTimesResponseDTO>> getOccupiedBookingTimes(
            @RequestParam Long activityId,
            @RequestParam String date
            ) {

        var localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        return ResponseEntity.ok(bookingService.getOccupiedBookingTimes(activityId, localDate));
    }
}
