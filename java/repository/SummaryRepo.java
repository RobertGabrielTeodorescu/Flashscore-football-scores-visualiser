package repository;

import entity.Match;
import entity.Summary;
import entity.Team;
import querries.SummaryQuerries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class SummaryRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertNewSummary(Summary summary) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(summary);
        em.getTransaction().commit();
        em.close();
    }

    public void updateSummary(Summary summary){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(summary);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteSummary(Summary summary){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(summary) ? summary : em.merge(summary));
        em.getTransaction().commit();
        em.close();
    }

    public Summary findSummary(String id_summary){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Summary summary = entityManager.find(Summary.class, id_summary);
        entityManager.getTransaction().commit();
        entityManager.close();
        return summary;
    }

    public List<String> getSummaries(String id_match){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        String sqlQuery = SummaryQuerries.FIND_SUMMARIES_BY_MATCH;
        Query query = em.createNativeQuery(sqlQuery);
        query.setParameter(1, id_match);
        List list = query.getResultList();
        em.getTransaction().commit();
        em.close();

        return list;
    }

}
