package kea.exam.template.booking;

import kea.exam.template.activity.ActivityService;
import kea.exam.template.booking_product.BookingProductRepository;
import kea.exam.template.exceptions.EntityNotFoundException;
import kea.exam.template.participant.Participant;
import kea.exam.template.participant.ParticipantRepository;
import kea.exam.template.product.Product;
import kea.exam.template.product.ProductService;
import kea.exam.template.product.dto.ProductBookingResponseDTO;
import kea.exam.template.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ActivityService activityService;
    private final ProductService productService;
    private final ParticipantRepository participantRepository;


    public BookingService(BookingRepository bookingRepository, UserService userService, ActivityService activityService, ProductService productService, ParticipantRepository participantRepository) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.activityService = activityService;
        this.productService = productService;
        this.participantRepository = participantRepository;

    }

    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<BookingResponseDTO> getBookingsByUserId(String id) {
        return bookingRepository.findBookingsByUserId(id)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public BookingResponseDTO updateBookingParticipants(Long id, List<String> participantNames) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking", id));

        Set<Participant> newParticipants = new HashSet<>();

        for (String name : participantNames) {
            Optional<Participant> participant = participantRepository.findByName(name);

            if (participant.isEmpty()) {
                var created = participantRepository.save(new Participant(name));
                newParticipants.add(created);
            } else {
                newParticipants.add(participant.get());
            }
        }

        booking.setParticipants(newParticipants);

        bookingRepository.save(booking);
        return toDTO(booking);
    }

    @Transactional
    public BookingResponseDTO deleteBookingById(Long id) {
        Booking bookingInDB = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking", id));
        BookingResponseDTO responseDTO = toDTO(bookingInDB);

        bookingRepository.deleteById(id);

        return responseDTO;
    }


    public BookingResponseDTO toDTO(Booking entity) {
        return new BookingResponseDTO(
                entity.getId(),
                entity.getTotalPrice(),
                formatDate(entity.getStartTime()),
                formatDate(entity.getEndTime()),
                userService.toDTO(entity.getUser()),
                activityService.toDTO(entity.getActivity()),
                entity.getParticipants()
                        .stream()
                        .map(Participant::getName)
                        .toList(),
                entity.getProducts()
                        .stream()
                        .map(bookingProduct -> productService.productBookingResponseDTO(bookingProduct.getQuantity(), bookingProduct.getProduct()))
                        .toList()
        );
    }


    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return dateTime.format(formatter);
    }
}
