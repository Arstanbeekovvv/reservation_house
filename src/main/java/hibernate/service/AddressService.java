package hibernate.service;

import hibernate.entities.Address;
import hibernate.entities.Agency;

import java.util.List;
import java.util.Map;

public interface AddressService {
    Address findById(Long id);
    List<Address> findAll();
    void update(Long id, Address address);
    Map<Address, Agency> getAddressWithAgency();
    int countAgencyByCityName(String cityName);
    Map<String, List<Agency>> groupByRegion();
}
