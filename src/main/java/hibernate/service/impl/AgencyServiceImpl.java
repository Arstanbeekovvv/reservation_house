package hibernate.service.impl;

import hibernate.Dao.AddressDao;
import hibernate.Dao.AgencyDao;
import hibernate.Dao.impl.AddressDaoImpl;
import hibernate.Dao.impl.AgencyDaoImpl;
import hibernate.entities.Address;
import hibernate.entities.Agency;
import hibernate.entities.House;
import hibernate.service.AddressService;
import hibernate.service.AgencyService;
import hibernate.service.HouseService;

import java.util.List;

public class AgencyServiceImpl implements AgencyService {
    AgencyDao agencyDao = new AgencyDaoImpl();
    @Override
    public void save(Agency object) {
        if(object.getPhoneNumber().startsWith("+996") && object.getAddress() != null) {
            AddressService addressService = new AddressServiceImpl();
            boolean t = false;
            for (Address address : addressService.findAll()) {
                if(address.getStreet().equals(object.getAddress().getStreet())){
                    t = true;
                }
            }
            if(!t) {
                agencyDao.save(object);
            }

        }else System.out.println("startWith: +996 !" +
                "/naddress != null !" +
                "/naddress.street unique !");
    }

    @Override
    public Agency findById(Long id) {
        return agencyDao.findById(id);
    }

    @Override
    public List<Agency> getAll() {
        return agencyDao.getAll();
    }

    @Override
    public void update(Long id, Agency object) {
        Agency byId = findById(id);
        if(byId != null) {
            agencyDao.update(id, object);
        }else System.out.println("NULL!!!");
    }

    @Override
    public void delete(Long id) {
        Agency agency = findById(id);
        if(agency != null) {
            agencyDao.delete(id);
        }else System.out.println("NULL!!!");
    }
}
