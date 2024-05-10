package kea.exam.template.product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kea.exam.template.booking.Booking;
import kea.exam.template.category.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "image_url")
    private String imageURL;

    private double price;

    private int stock;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();

    public Product(String name, String imageURL, double price, int stock, Category category) {
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public Product(Long id, String name, String imageURL, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }
}
