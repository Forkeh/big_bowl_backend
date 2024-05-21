package kea.exam.template.booking;

import jakarta.persistence.*;
import kea.exam.template.activity.Activity;

import kea.exam.template.booking_product.BookingProduct;
import kea.exam.template.participant.Participant;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "booking_id")
    private Set<BookingProduct> products = new HashSet<>();


    public Booking(Long id, double totalPrice, LocalDateTime startTime, LocalDateTime endTime, User user, Activity activity, Set<Participant> participants) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.activity = activity;
        this.participants = participants;
    }

    public Booking(double totalPrice, LocalDateTime startTime, LocalDateTime endTime, User user, Activity activity, Set<Participant> participants) {
        this(null, totalPrice, startTime, endTime, user, activity, participants);
    }

}
