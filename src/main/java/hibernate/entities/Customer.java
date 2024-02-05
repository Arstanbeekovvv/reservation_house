package hibernate.entities;

import hibernate.BaseEntities;
import hibernate.enums.FamilyStatus;
import hibernate.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen", sequenceName = "customer_seq", allocationSize = 1)
public class Customer  extends BaseEntities {
    @Column(name = "first_name") private String firstName;
    @Column(name = "last_name") private String lastName;
    private String email;
    @Column(name = "date_of_birth")     private LocalDate dateOfBirth;
    @Column private Gender gender;
    private String nationality;
    @Column(name = "family_status")     private FamilyStatus familyStatus;

    @OneToMany(mappedBy = "customer",cascade = {PERSIST})
    @ToString.Exclude  private List<RentInfo> rentInfos = new ArrayList<>();

    public Customer(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender, String nationality, FamilyStatus familyStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
    }
}
