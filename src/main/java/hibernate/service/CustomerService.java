package hibernate.service;

import hibernate.entities.Customer;
import hibernate.entities.RentInfo;

import java.time.LocalDate;

public interface CustomerService extends CRUDService<Customer>{
    void saveWithRentInfo(Customer customer, RentInfo rentInfo);
    void rentInfoHouse(Long customerId, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);
}
