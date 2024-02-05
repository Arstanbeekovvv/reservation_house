package hibernate.Dao.impl;

import hibernate.Dao.AddressDao;
import hibernate.config.DataBaseConnection;
import hibernate.entities.Address;
import hibernate.entities.Agency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDaoImpl implements AddressDao {
    EntityManagerFactory entityManagerFactory = DataBaseConnection.getEntityManager();

    @Override
    public Address findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address address = new Address();
        try{
            entityManager.getTransaction().begin();
            address = entityManager.find(Address.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return address;
    }

    @Override
    public List<Address> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addresses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            addresses = entityManager.createQuery("select a from Address a", Address.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return addresses;
    }

    @Override
    public void update(Long id, Address address) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Address address1 = entityManager.find(Address.class, id);
            address1.setCity(address.getCity());
            address1.setRegion(address.getRegion());
            address1.setStreet(address.getStreet());
            entityManager.merge(address1);
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
    public Map<Address, Agency> getAddressWithAgency() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<Address, Agency> addressWithAgency = new HashMap<>();
        try{
            entityManager.getTransaction().begin();
            List<Address> selectAFromAgencyA = entityManager.createQuery("select a from Address a", Address.class).getResultList();
            for (Address address : selectAFromAgencyA) {
                addressWithAgency.put(address, address.getAgency());
            }
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return addressWithAgency;
    }

    @Override
    public int countAgencyByCityName(String cityName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int countAgency = 0;
        try{
            entityManager.getTransaction().begin();

            countAgency = entityManager.
                    createQuery("select count(*) from Agency a where a.address.city = :city_name", Agency.class).
                    setParameter("city_name", cityName).executeUpdate();

            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return countAgency;
    }

    @Override
    public Map<String, List<Agency>> groupByRegion() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Map<String, List<Agency>> listMap = new HashMap<>();
        try{
            entityManager.getTransaction().begin();
            List<Agency> selectAFromAgencyA = entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
            List<Address> addresses = entityManager.createQuery("select distinct a.region from Address a", Address.class).getResultList();
            for (Address address : addresses) {
                List<Agency> agencies = new ArrayList<>();
                for (Agency address1 : selectAFromAgencyA) {
                    if(address.getRegion().equals(address1.getAddress().getRegion())){
                        agencies.add(address1);
                    }
                }
                listMap.put(address.getRegion(), agencies);
            }
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return listMap;
    }
}
