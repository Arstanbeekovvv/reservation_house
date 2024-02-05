package hibernate.entities;
import hibernate.BaseEntities;
import hibernate.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "owners")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(name = "base_id_gen", sequenceName = "owner_seq", allocationSize = 1)
public class Owner  extends BaseEntities {
    @Column(name = "first_name")        private String firstName;
    @Column(name = "last_name")         private String lastName;
    @Column(unique = true)              private String email;
    @Column(name = "date_of_birth")     private LocalDate dateOfBirth;
    @Column     private Gender gender;

    @OneToMany(mappedBy = "owner", cascade = {REMOVE, PERSIST, MERGE, REFRESH}) @ToString.Exclude      private List<House> houses;
    @ManyToMany(cascade = {PERSIST,MERGE, DETACH})  @ToString.Exclude                       private List<Agency> agencies;
    @OneToMany(mappedBy = "owner", cascade = {PERSIST}) @ToString.Exclude     private List<RentInfo> rentInfos;

    public Owner(String firstName, String lastName, String email, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public void addHouse(House house){
        if(houses == null){
            houses = new ArrayList<>();
        }
        houses.add(house);
    }

    public void addAgency(Agency agency){
        if(agencies == null){
            agencies = new ArrayList<>();
        }
        agencies.add(agency);
    }

    public void addRentInfo(RentInfo rentInfo){
        if(rentInfos == null){
            rentInfos = new ArrayList<>();
        }
        rentInfos.add(rentInfo);
    }

}
