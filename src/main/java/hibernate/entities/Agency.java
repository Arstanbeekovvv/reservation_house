package hibernate.entities;

import hibernate.BaseEntities;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen", sequenceName = "agency_seq", allocationSize = 1)
public class Agency  extends BaseEntities {
    private String name;
    @Column(name = "phone_number", length = 13)
    private String phoneNumber;

    @OneToOne(cascade = {REMOVE, MERGE, PERSIST})  @ToString.Exclude            private Address address;
    @OneToMany(cascade = {REMOVE})  @ToString.Exclude           private List<RentInfo>  rentInfos = new ArrayList<>();
    @ManyToMany(mappedBy = "agencies")  @ToString.Exclude       private List<Owner> owners = new ArrayList<>();

    public Agency(String name, String phoneNumber, Address address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
