package kea.exam.template.booking_product;

import jakarta.persistence.*;
import kea.exam.template.booking.Booking;
import kea.exam.template.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingProduct {

    @EmbeddedId
    private BookingProductKey id;

    private int quantity;

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Booking getBooking() {
        return id.getBooking();
    }

    public void setBooking(Booking booking) {
        id.setBooking(booking);
    }
}
