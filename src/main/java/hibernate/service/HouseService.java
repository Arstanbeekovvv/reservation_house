package hibernate.service;

import hibernate.entities.House;

import java.time.LocalDate;
import java.util.List;

public interface HouseService extends CRUDService<House>{
    void saveHouse(House house, Long id);
    List<House> getAllHouseByRegionName(String region);
    List<House> getAllHouseByAgencyId(Long agency);
    List<House> getAllHouseByOwnerId(Long ownerId);
    List<House> getHousesByCheckin(LocalDate localDate1, LocalDate localDate2);

}
