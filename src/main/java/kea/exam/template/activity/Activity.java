package kea.exam.template.activity;

import jakarta.persistence.*;
import kea.exam.template.type.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean isOpen;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Activity(String name, boolean isOpen, Type type) {
        this.name = name;
        this.isOpen = isOpen;
        this.type = type;
    }

}
