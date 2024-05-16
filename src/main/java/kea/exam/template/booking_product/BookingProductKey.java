package kea.exam.template.booking_product;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import kea.exam.template.booking.Booking;
import kea.exam.template.product.Product;

@Embeddable
public class BookingProductKey implements Serializable {

    @ManyToOne
    private Product product;

    @ManyToOne
    private Booking booking;

    // Default constructor
    public BookingProductKey() {}

    // Parameterized constructor
    public BookingProductKey(Product product, Booking booking) {
        this.product = product;
        this.booking = booking;
    }

    // Getters and setters
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    // hashCode and equals
    @Override
    public int hashCode() {
        return Objects.hash(product, booking);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BookingProductKey that = (BookingProductKey) obj;
        return Objects.equals(product, that.product) &&
                Objects.equals(booking, that.booking);
    }
}
