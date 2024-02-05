package hibernate.Dao.impl;

import hibernate.Dao.AgencyDao;
import hibernate.config.DataBaseConnection;
import hibernate.entities.Agency;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class AgencyDaoImpl implements AgencyDao {
    EntityManagerFactory entityManagerFactory = DataBaseConnection.getEntityManager();

    @Override
    public void save(Agency object) {
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
    public Agency findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Agency agency = null;
        try{
            entityManager.getTransaction().begin();
            agency = entityManager.find(Agency.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return agency;
    }

    @Override
    public List<Agency> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Agency> agencies = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            agencies = entityManager.createQuery("select a from Agency a", Agency.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return agencies;
    }

    @Override
    public void update(Long id, Agency object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Agency agency = entityManager.find(Agency.class, id);
            agency.setName(object.getName());
            agency.setPhoneNumber(object.getPhoneNumber());
            entityManager.merge(agency);
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
            Agency agency = entityManager.find(Agency.class, id);
            entityManager.remove(agency);
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
