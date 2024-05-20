package kea.exam.template.booking_product;

import kea.exam.template.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookingProductRepository extends JpaRepository<BookingProduct, Long> {

}
