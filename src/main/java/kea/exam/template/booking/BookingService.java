package kea.exam.template.booking;

import kea.exam.template.activity.Activity;
import kea.exam.template.activity.ActivityRepository;
import kea.exam.template.activity.ActivityService;
import kea.exam.template.booking.dto.BookingOccupiedTimesResponseDTO;
import kea.exam.template.booking.dto.BookingRequestDTO;
import kea.exam.template.booking.dto.BookingResponseDTO;
import kea.exam.template.booking.dto.IProductRequest;
import kea.exam.template.booking_product.BookingProduct;
import kea.exam.template.booking_product.BookingProductRepository;
import kea.exam.template.exceptions.BadRequestException;
import kea.exam.template.exceptions.EntityNotFoundException;
import kea.exam.template.participant.Participant;
import kea.exam.template.participant.ParticipantRepository;
import kea.exam.template.product.Product;
import kea.exam.template.product.ProductRepository;
import kea.exam.template.product.ProductService;
import kea.exam.template.user.User;
import kea.exam.template.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ActivityService activityService;
    private final ProductService productService;
    private final ParticipantRepository participantRepository;
    private final ActivityRepository activityRepository;
    private final ProductRepository productRepository;
    private final BookingProductRepository bookingProductRepository;


    public BookingService(BookingRepository bookingRepository, UserService userService, ActivityService activityService, ProductService productService, ParticipantRepository participantRepository, ActivityRepository activityRepository, ProductRepository productRepository, BookingProductRepository bookingProductRepository) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.activityService = activityService;
        this.productService = productService;
        this.participantRepository = participantRepository;
        this.activityRepository = activityRepository;
        this.productRepository = productRepository;
        this.bookingProductRepository = bookingProductRepository;
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

        Set<Participant> newParticipants = findOrCreateParticipants(participantNames);

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

    public List<BookingOccupiedTimesResponseDTO> getOccupiedBookingTimes(Long activityId, LocalDate localDate) {
        List<Booking> bookingsInDB = bookingRepository.findAllByStartTimeBetweenAndActivityId(
                LocalDateTime.of(localDate, LocalTime.MIN),
                LocalDateTime.of(localDate, LocalTime.MAX),
                activityId
        );

        List<BookingOccupiedTimesResponseDTO> result = new ArrayList<>();

        for (Booking booking : bookingsInDB) {
            var start = booking.getStartTime();
            var end = booking.getEndTime();

            Long duration = Duration.between(start, end)
                    .toHours();
            String startTime = start.getHour() + ":00";

            result.add(new BookingOccupiedTimesResponseDTO(
                    duration,
                    startTime
            ));
        }

        return result;
    }

    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO booking) {
        //TODO: Stock og quantity. Husk at delete fra stock også.


        Activity activity = activityRepository.findById(booking.activityId()).
                orElseThrow(() -> new EntityNotFoundException("Booking", booking.activityId()));

        User user = userService.findUserById(booking.userId()).
                orElseThrow(() -> new EntityNotFoundException("User", booking.userId()));

        Set<Participant> participants = findOrCreateParticipants(booking.participants());

        LocalDateTime startDate = Instant.parse(booking.start())
                .atZone(ZoneId.of("Europe/Copenhagen"))
                .toLocalDateTime();

        Booking newBooking = new Booking(
                0,
                startDate,
                startDate.plusHours(booking.duration()),
                user,
                activity,
                participants
        );

        bookingRepository.save(newBooking);

        Set<BookingProduct> bookingProducts = new HashSet<>();


        double price = activity.getType()
                .getPrice();

        for (IProductRequest Iproduct : booking.products()) {
            Product product = productRepository.findById(Iproduct.id()).
                    orElseThrow(() -> new EntityNotFoundException("Product", Iproduct.id()));

            if (product.getStock() < Iproduct.quantity()) {
                throw new BadRequestException("Quantity for " + product.getName() + " exceeds the total stock of " + product.getStock());
            }

            product.setStock(product.getStock() - Iproduct.quantity());
            productRepository.save(product);
            price += product.getPrice() * Iproduct.quantity();
            bookingProducts.add(new BookingProduct(product, newBooking, Iproduct.quantity()));
        }

        newBooking.setTotalPrice(price);
        bookingRepository.save(newBooking);

        bookingProductRepository.saveAll(bookingProducts);

        return toDTO(newBooking);
    }

    private Set<Participant> findOrCreateParticipants(List<String> participants) {

        Set<Participant> newParticipants = new HashSet<>();

        for (String name : participants) {
            Optional<Participant> participant = participantRepository.findByName(name);

            if (participant.isEmpty()) {
                var created = participantRepository.save(new Participant(name));
                newParticipants.add(created);
            } else {
                newParticipants.add(participant.get());
            }
        }
        return newParticipants;
    }
}
