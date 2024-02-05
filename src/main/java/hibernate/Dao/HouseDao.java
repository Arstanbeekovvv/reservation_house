package hibernate.Dao;

import hibernate.entities.House;
import java.time.LocalDate;
import java.util.List;

public interface HouseDao extends CRUD<House>{
    void saveHouse(House house, Long id);
    List<House> getAllHouseByRegionName(String region);
    List<House> getAllHouseByAgencyId(Long agency);
    List<House> getAllHouseByOwnerId(Long ownerId);
    List<House> getHousesByCheckin(LocalDate localDate1, LocalDate localDate2);
}
