package hibernate.service.impl;

import hibernate.Dao.CustomerDao;
import hibernate.Dao.impl.CustomerDaoImpl;
import hibernate.entities.Customer;
import hibernate.entities.RentInfo;
import hibernate.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public void save(Customer object) {
        customerDao.save(object);
    }

    @Override
    public Customer findById(Long id) {
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public void update(Long id, Customer object) {
        Customer customer = findById(id);
        if(customer != null) {
            customerDao.update(id, object);
        }else System.out.println("NULL!!!");
    }

    @Override
    public void delete(Long id) {
        Customer customer = findById(id);
        if(customer != null) {
            if(customer.getRentInfos() == null) {
                customerDao.delete(id);
            } else{
                for (RentInfo info : customer.getRentInfos()) {
                    if(LocalDate.now().isAfter(info.getCheckout())){
                        System.out.println("alskfdj");
                        customerDao.delete(id);
                        break;
                    }
                }
            }

        }else System.out.println("NULL!!! or customer has rentInfo !");
    }

    @Override
    public void saveWithRentInfo(Customer customer, RentInfo rentInfo) {
        customer.getRentInfos().add(rentInfo);
        customerDao.saveWithRentInfo(customer);
    }

    @Override
    public void rentInfoHouse(Long customerId, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        if(findById(customerId) != null) {
            customerDao.rentInfoHouse(customerId, houseId, agencyId, checkIn, checkOut);
        }else System.out.println("Customer not found!!!");
    }
}
