package hibernate.entities;
import hibernate.BaseEntities;
import hibernate.enums.HouseType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SequenceGenerator(name = "base_id_gen", sequenceName = "house_seq", allocationSize = 1)
public class House  extends BaseEntities {
    @Column(name = "house_type") private HouseType houseType;
    private BigDecimal price;
    private Double rating;
    private String description;
    private int room;
    private Boolean furniture;

    @OneToOne(cascade = {REMOVE, PERSIST})
    @ToString.Exclude private Address address;
    @OneToOne(cascade = {PERSIST})
    @ToString.Exclude private RentInfo rentInfo;
    @ManyToOne(cascade = {MERGE, PERSIST})
    @ToString.Exclude private Owner owner;

    public House(HouseType houseType, BigDecimal price, Double rating, String description, int room, Boolean furniture, Address address) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
        this.address = address;
    }

    public House(HouseType houseType, BigDecimal price, Double rating, String description, int room, Boolean furniture) {
        this.houseType = houseType;
        this.price = price;
        this.rating = rating;
        this.description = description;
        this.room = room;
        this.furniture = furniture;
    }
}
