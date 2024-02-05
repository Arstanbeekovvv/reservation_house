package hibernate;

import jakarta.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter @Setter
public class BaseEntities {
    @Id
    @GeneratedValue(generator = "base_id_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Override
    public String toString() {
        return "id = " + id + " ";
    }
}
