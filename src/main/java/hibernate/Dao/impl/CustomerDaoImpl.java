package hibernate.Dao.impl;

import hibernate.Dao.CustomerDao;
import hibernate.config.DataBaseConnection;
import hibernate.entities.Agency;
import hibernate.entities.Customer;
import hibernate.entities.House;
import hibernate.entities.RentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    EntityManagerFactory entityManagerFactory = DataBaseConnection.getEntityManager();

    @Override
    public void save(Customer object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public Customer findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Customer customer = null;
        try{
            entityManager.getTransaction().begin();
            customer = entityManager.find(Customer.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Customer> customers = null;
        try{
            entityManager.getTransaction().begin();
            customers = entityManager.createQuery("select c from Customer c", Customer.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return customers;
    }

    @Override
    public void update(Long id, Customer object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            customer.setFirstName(object.getFirstName());
            customer.setLastName(object.getLastName());
            customer.setEmail(object.getEmail());
            customer.setGender(object.getGender());
            customer.setDateOfBirth(object.getDateOfBirth());
            customer.setFamilyStatus(object.getFamilyStatus());
            customer.setNationality(object.getNationality());
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Customer customer = entityManager.find(Customer.class, id);
            entityManager.remove(customer);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void saveWithRentInfo(Customer customer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }

    @Override
    public void rentInfoHouse(Long customerId, Long houseId, Long agencyId, LocalDate checkIn, LocalDate checkOut) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            RentInfo rentInfo = new RentInfo();
            rentInfo.setCheckin(checkIn);
            rentInfo.setCheckout(checkOut);
            Customer customer = entityManager.find(Customer.class, customerId);
            House house = entityManager.find(House.class, houseId);
            Agency agency = entityManager.find(Agency.class, agencyId);
            customer.getRentInfos().add(rentInfo);
            house.setRentInfo(rentInfo);
            agency.getRentInfos().add(rentInfo);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
