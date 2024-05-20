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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Booking booking;

    private int quantity;

    public BookingProduct(Product product, Booking booking, int quantity) {
        this.product = product;
        this.booking = booking;
        this.quantity = quantity;
    }

}
