package kea.exam.template.booking;

import kea.exam.template.activity.Activity;
import kea.exam.template.activity.ActivityService;
import kea.exam.template.activity.dto.ActivityResponseDTO;
import kea.exam.template.booking.dto.BookingResponseDTO;
import kea.exam.template.booking_product.BookingProductRepository;
import kea.exam.template.exceptions.EntityNotFoundException;
import kea.exam.template.participant.Participant;
import kea.exam.template.user.User;
import kea.exam.template.user.UserService;
import kea.exam.template.user.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {


    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookingProductRepository bookingProductRepository;

    @Mock
    private UserService userService;

    @Mock
    private ActivityService activityService;

    @InjectMocks
    private BookingService bookingService;


    //@Test
    void deleteBookingWhichExists() {
        // Arrange
        Long bookingId = 10L;

        var bookingInDb = new Booking(
                bookingId,
                417,
                LocalDateTime.now()
                        .plusHours(1),
                LocalDateTime.now()
                        .plusHours(4),
                new User("1", "first", "last"),
                new Activity(),
                new HashSet<>(
                        Set.of(
                                new Participant("Ali"), new Participant("Brian")
                        )));

        var bookingDTO = new BookingResponseDTO(
                bookingInDb.getId(),
                bookingInDb.getTotalPrice(),
                bookingInDb.getStartTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                bookingInDb.getEndTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")),
                new UserDTO("1", "first", "last"),
                new ActivityResponseDTO(null, null, null, null, null),
                bookingInDb.getParticipants()
                        .stream()
                        .map(Participant::getName)
                        .toList(),
                new ArrayList<>()
        );

        Mockito.when(bookingRepository.findById(bookingId))
                .thenReturn(Optional.of(bookingInDb));
        Mockito.when(userService.toDTO(bookingInDb.getUser()))
                .thenReturn(bookingDTO.user());
        Mockito.when(activityService.toDTO(bookingInDb.getActivity()))
                .thenReturn(bookingDTO.activity());
        //Mockito.when(bookingProductRepository.deleteAllByBookingId(bookingId)).thenReturn(null);
        //Mockito.when(bookingRepository.delete(bookingInDb)).thenReturn(null);
        Mockito.when(bookingService.toDTO(bookingInDb))
                .thenReturn(bookingDTO);


        // Act
        BookingResponseDTO response = bookingService.deleteBookingById(bookingId);

        // Assert
        assertEquals(response.id(), bookingId);
    }

    @Test
    void deleteBookingWitchDoesNotExist() {
        // Arrange
        Long bookingId = 10L;
        Mockito.when(bookingRepository.findById(bookingId))
                .thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(EntityNotFoundException.class, () -> bookingService.deleteBookingById(bookingId));
    }

}