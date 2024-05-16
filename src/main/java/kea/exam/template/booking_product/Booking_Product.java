package kea.exam.template.booking_product;

import jakarta.persistence.*;
import kea.exam.template.booking.Booking;
import kea.exam.template.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Booking_Product {

    @ManyToOne
    @Id
    private Product product;

    @ManyToOne
    @Id
    private Booking booking;

    private int quantity;
}
