package hibernate.Dao.impl;

import hibernate.Dao.RentInfoDao;
import hibernate.config.DataBaseConnection;
import hibernate.entities.Agency;
import hibernate.entities.RentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class RentInfoImpl implements RentInfoDao {
    EntityManagerFactory entityManagerFactory = DataBaseConnection.getEntityManager();
    @Override
    public List<RentInfo> getRentInfoBetween(LocalDate localDate1, LocalDate localDate2) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RentInfo> rentInfos = null;
        try{
            entityManager.getTransaction().begin();
            rentInfos = entityManager.
                    createQuery("select r from RentInfo r " +
                            "where r.checkout between :one and :two", RentInfo.class).
                    setParameter("one", localDate1).
                    setParameter("two", localDate2).
                    getResultList();
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
    public Integer getCountOfRentInfoByAgencyId(Long agencyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int count = 0;
        try{
            entityManager.getTransaction().begin();
            List<Agency> agencies = entityManager.createQuery("select a from Agency a where a = :id_agency", Agency.class).getResultList();
            for (Agency agency : agencies) {
                for (RentInfo rentInfo : agency.getRentInfos()) {
                    if(rentInfo.getCheckout() != null){
                      count++;
                    }
                }
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
        return count;
    }
}
