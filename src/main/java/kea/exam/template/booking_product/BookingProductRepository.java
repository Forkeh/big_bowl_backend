package kea.exam.template.booking_product;

import kea.exam.template.booking.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookingProductRepository extends JpaRepository<BookingProduct,BookingProductKey > {
    void deleteBookingProductsById_Booking_Id(Long id);

    void deleteBookingProductsById_Booking(Booking booking);

    @Modifying
    @Transactional()
    @Query(value = "DELETE FROM booking_product WHERE booking_id = ?1", nativeQuery = true)
    void deleteAllByBookingId(Long id);


}
