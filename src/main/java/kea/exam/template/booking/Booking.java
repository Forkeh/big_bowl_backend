package kea.exam.template.booking;

import jakarta.persistence.*;
import kea.exam.template.activity.Activity;
import kea.exam.template.booking_product.Booking_Product;
import kea.exam.template.participant.Participant;
import kea.exam.template.product.Product;
import kea.exam.template.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double totalPrice;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Activity activity;

    @ManyToMany
    private Set<Participant> participants = new HashSet<>();

    /*@ManyToMany
    private Set<Product> products = new HashSet<>();*/

    @OneToMany
    @JoinColumn(name = "booking_id")
    private Set<Booking_Product> products = new HashSet<>();

    public Booking(double totalPrice, LocalDateTime startTime, LocalDateTime endTime, User user, Activity activity, Set<Participant> participants) {
        this.totalPrice = totalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.activity = activity;
        this.participants = participants;
    }
}
