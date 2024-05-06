package kea.exam.template.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import kea.exam.template.booking.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private Set<Booking> bookings = new HashSet<>();
}
