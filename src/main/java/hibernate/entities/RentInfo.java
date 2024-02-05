package hibernate.entities;
import hibernate.BaseEntities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rent_info")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen", sequenceName = "rent_info_seq", allocationSize = 1)
public class RentInfo  extends BaseEntities {
    @Column(name = "check_in")       private LocalDate checkin;
    @Column(name = "check_out")      private LocalDate checkout;

    @ManyToOne @ToString.Exclude private Agency agency;
    @ManyToOne @ToString.Exclude private Owner owner;
    @ManyToOne @ToString.Exclude private Customer customer;


}
