package hibernate.service;

import hibernate.entities.House;
import hibernate.entities.Owner;

public interface OwnerService extends CRUDService<Owner>{
    void saveWithHouse(Owner owner, House house);
    void getNameAndAgeOwner();
}
