package repository;

import entity.Lineup;
import querries.LineupQuerries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class LineupRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertLineup(Lineup lineup){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(lineup);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<String> getLineups(String id_match){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        String sqlQuery = LineupQuerries.FIND_LINEUPS_BY_MATCH;
        Query query = em.createNativeQuery(sqlQuery);
        query.setParameter(1, id_match);
        List list = query.getResultList();
        em.getTransaction().commit();
        em.close();

        return list;
    }


    public Lineup findLineup(String id_lineup){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Lineup lineup = entityManager.find(Lineup.class, id_lineup);
        entityManager.getTransaction().commit();
        entityManager.close();
        return lineup;
    }

    public void deleteLineup(Lineup lineup){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(lineup) ? lineup : entityManager.merge(lineup));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateLineup(Lineup lineup){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(lineup);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
