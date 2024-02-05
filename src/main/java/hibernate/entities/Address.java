package hibernate.entities;

import hibernate.BaseEntities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen", sequenceName = "address_seq", allocationSize = 1)
public class Address extends BaseEntities {
    private String city;
    private String region;
    private String street;

    @OneToOne(mappedBy = "address") @ToString.Exclude private Agency agency;

    public Address(String city, String region, String street) {
        this.city = city;
        this.region = region;
        this.street = street;
    }
}
