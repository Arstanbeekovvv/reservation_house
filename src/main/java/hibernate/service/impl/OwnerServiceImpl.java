package hibernate.service.impl;

import hibernate.Dao.OwnerDao;
import hibernate.Dao.impl.OwnerDaoImpl;
import hibernate.entities.Customer;
import hibernate.entities.House;
import hibernate.entities.Owner;
import hibernate.entities.RentInfo;
import hibernate.service.OwnerService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class OwnerServiceImpl implements OwnerService {
    OwnerDao ownerDao = new OwnerDaoImpl();
    @Override
    public void save(Owner object) {
        ownerDao.save(object);
    }

    @Override
    public Owner findById(Long id) {
        return ownerDao.findById(id);
    }

    @Override
    public List<Owner> getAll() {
        return ownerDao.getAll();
    }

    @Override
    public void update(Long id, Owner object) {
        Owner owner = findById(id);
        if(owner != null) {
            ownerDao.update(id, object);
        }else System.out.println("NULL!!!");
    }

    @Override
    public void delete(Long id) {
        Owner owner = findById(id);
        if(owner != null) {
            if(owner.getRentInfos() == null) {
                ownerDao.delete(id);
            } else{
                for (RentInfo info : owner.getRentInfos()) {
                    if(LocalDate.now().isAfter(info.getCheckout())){
                        ownerDao.delete(id);
                    }
                    break;
                }

            }

        }else System.out.println("NULL!!! or customer has rentInfo !");
    }

    @Override
    public void saveWithHouse(Owner owner, House house) {
        try {
            if (owner.getDateOfBirth().compareTo(LocalDate.now()) <= 18) {
                owner.addHouse(house);
                house.setOwner(owner);
                ownerDao.saveWithHouse(owner);
            } else System.out.println("Owner doesn't 18 years!");
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void getNameAndAgeOwner() {
        List<Owner> owners = getAll();
        owners.forEach(owner -> System.out.println("First name: " + owner.getFirstName() + " age: " + Period.between(owner.getDateOfBirth(), LocalDate.now()).getYears()));
    }
}
