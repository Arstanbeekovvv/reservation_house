package hibernate.Dao;

import hibernate.entities.Owner;

import java.util.List;

public interface OwnerDao extends CRUD<Owner>{
    void saveWithHouse(Owner owner);
    void assignOwnerToAgency(Long ownerId, Long agencyId);
    List<Owner> getOwnersByAgencyId(Long id);
}
