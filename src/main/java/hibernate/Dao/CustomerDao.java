package hibernate.Dao;

import hibernate.entities.Customer;

import java.time.LocalDate;

public interface CustomerDao extends CRUD<Customer>{
    void saveWithRentInfo(Customer customer);

    void rentInfoHouse(Long customerId, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut);
}
