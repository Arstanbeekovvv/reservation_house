package hibernate.Dao.impl;

import hibernate.Dao.HouseDao;
import hibernate.config.DataBaseConnection;
import hibernate.entities.Agency;
import hibernate.entities.House;
import hibernate.entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    EntityManagerFactory entityManagerFactory = DataBaseConnection.getEntityManager();


    @Override
    public House findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        House house = null;
        try{
            entityManager.getTransaction().begin();
            house = entityManager.find(House.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return house;
    }

    @Override
    public List<House> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h", House.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public void update(Long id, House object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            House house = entityManager.find(House.class, id);
            house.setPrice(object.getPrice());
            house.setHouseType(object.getHouseType());
            house.setDescription(object.getDescription());
            house.setFurniture(object.getFurniture());
            house.setRoom(object.getRoom());
            house.setRating(object.getRating());
            entityManager.merge(house);
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
            House house = entityManager.find(House.class, id);
            entityManager.remove(house);
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
    public void saveHouse(House house, Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);
            entityManager.persist(house);
            owner.addHouse(house);
            house.setOwner(owner);
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
    public List<House> getAllHouseByRegionName(String region) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h where h.address.region = :regionName", House.class).setParameter("regionName", region).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getAllHouseByAgencyId(Long agency) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            Agency agency1 = entityManager.find(Agency.class, agency);
//            houses.getFirst().
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public List<House> getAllHouseByOwnerId(Long ownerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            houses.addAll(owner.getHouses());
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return houses;
    }

    @Override
    public List<House> getHousesByCheckin(LocalDate localDate1, LocalDate localDate2) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<House> houses = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            houses = entityManager.createQuery("select h from House h where h.rentInfo.checkin between :checkin1 and :checkin2", House.class).setParameter("checkin1", localDate1).setParameter("checkin2", localDate2).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return null;
    }












    // don't work
    @Override
    public void save(House object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();

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
