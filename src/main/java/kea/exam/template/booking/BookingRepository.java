package kea.exam.template.booking;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingsByUserId(String id);

    List<Booking> findAllByStartTimeBetweenAndActivityId(LocalDateTime startTime, LocalDateTime endTime, Long id);
}
