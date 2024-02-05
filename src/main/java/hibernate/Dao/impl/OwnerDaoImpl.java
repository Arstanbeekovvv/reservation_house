package hibernate.Dao.impl;

import hibernate.Dao.OwnerDao;
import hibernate.config.DataBaseConnection;
import hibernate.entities.Agency;
import hibernate.entities.House;
import hibernate.entities.Owner;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    EntityManagerFactory entityManagerFactory= DataBaseConnection.getEntityManager();

    @Override
    public void save(Owner object) {
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
    public Owner findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Owner owner = new Owner();
        try{
            entityManager.getTransaction().begin();
            owner = entityManager.find(Owner.class, id);
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return owner;
    }

    @Override
    public List<Owner> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Owner> owners = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            owners = entityManager.createQuery("select o from Owner o", Owner.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return owners;
    }

    @Override
    public void update(Long id, Owner object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, id);
            if(object.getFirstName() != null) owner.setFirstName(object.getFirstName());
            if(object.getLastName() != null) owner.setLastName(object.getLastName());
            if(object.getEmail() != null) owner.setEmail(object.getEmail());
            if(object.getDateOfBirth() != null) owner.setDateOfBirth(object.getDateOfBirth());
            if(object.getGender() != null) owner.setGender(object.getGender());
            entityManager.merge(owner);
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
            Owner owner = entityManager.find(Owner.class, id);
            entityManager.remove(owner);
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
    public void saveWithHouse(Owner owner) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
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
    public void assignOwnerToAgency(Long ownerId, Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, ownerId);
            Agency agency = entityManager.find(Agency.class, agencyId);
            owner.getAgencies().add(agency);
            agency.getOwners().add(owner);
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
    public List<Owner> getOwnersByAgencyId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Owner> owners = new ArrayList<>();
        try{
            entityManager.getTransaction().begin();
            List<Owner> ownerList = entityManager.
                    createQuery("select o from Owner o " +
                            "where o.id in (select o.id from Agency " +
                            "where id = :agencyId)", Owner.class).
                    setParameter("agencyId", id).getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return owners;
    }
}
