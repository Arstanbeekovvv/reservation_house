package hibernate.service.impl;

import hibernate.Dao.AddressDao;
import hibernate.Dao.impl.AddressDaoImpl;
import hibernate.entities.Address;
import hibernate.entities.Agency;
import hibernate.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();

    @Override
    public Address findById(Long id) {
        return addressDao.findById(id);
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public void update(Long id, Address address) {
        Address address1 = findById(id);
        if(address1 != null) {
            addressDao.update(id, address);
        }else System.out.println("NULL!!!");
    }

    @Override
    public Map<Address, Agency> getAddressWithAgency() {
        return addressDao.getAddressWithAgency();
    }

    @Override
    public int countAgencyByCityName(String cityName) {
        return addressDao.countAgencyByCityName(cityName);
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        return addressDao.groupByRegion();
    }

}
