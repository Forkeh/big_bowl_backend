package kea.exam.template.activity;


import jakarta.persistence.*;
import kea.exam.template.type.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isOpen;

    @ManyToOne
    private Type type;

}
