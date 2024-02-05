package hibernate.service.impl;

import hibernate.Dao.AddressDao;
import hibernate.Dao.AgencyDao;
import hibernate.Dao.HouseDao;
import hibernate.Dao.OwnerDao;
import hibernate.Dao.impl.AddressDaoImpl;
import hibernate.Dao.impl.AgencyDaoImpl;
import hibernate.Dao.impl.HouseDaoImpl;
import hibernate.Dao.impl.OwnerDaoImpl;
import hibernate.entities.Address;
import hibernate.entities.Agency;
import hibernate.entities.House;
import hibernate.service.HouseService;

import java.time.LocalDate;
import java.util.List;

public class HouseServiceImpl implements HouseService {
    HouseDao houseDao = new HouseDaoImpl();
    @Override
    public void save(House object) {
        houseDao.save(object);
    }

    @Override
    public House findById(Long id) {
        return houseDao.findById(id);
    }

    @Override
    public List<House> getAll() {
        return houseDao.getAll();
    }

    @Override
    public void update(Long id, House object) {
        House house = findById(id);
        if(house != null) {
            houseDao.update(id, object);
        }else System.out.println("NULL!!!");
    }

    @Override
    public void delete(Long id) {
        House house = findById(id);
        if(house != null) {
            if(house.getRentInfo() == null) {
                if(LocalDate.now().isAfter(house.getRentInfo().getCheckout())){
                    houseDao.delete(id);
                }else System.out.println("Not applicable chock out not found");
            }else System.out.println("RentInfo not is null!");
        }else System.out.println("NULL!!!");
    }

    @Override
    public void saveHouse(House house, Long id) {
        houseDao.saveHouse(house, id);
    }

    @Override
    public List<House> getAllHouseByRegionName(String region) {
        AddressDao addressDao = new AddressDaoImpl();
        for (Address address : addressDao.findAll()) {
            if(address.getRegion().equals(region)){
                return houseDao.getAllHouseByRegionName(region);

            }
        }
        return null;
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agency) {
        AgencyDao agencyDao = new AgencyDaoImpl();
        return agencyDao.findById(agency) != null
                ? houseDao.getAllHouseByAgencyId(agency): null;
    }

    @Override
    public List<House> getAllHouseByOwnerId(Long ownerId) {
        OwnerDao ownerDao = new OwnerDaoImpl();
        return ownerDao.findById(ownerId) != null
                ? houseDao.getAllHouseByAgencyId(ownerId): null;
    }

    @Override
    public List<House> getHousesByCheckin(LocalDate localDate1, LocalDate localDate2) {
        return houseDao.getHousesByCheckin(localDate1, localDate2);
    }
}
