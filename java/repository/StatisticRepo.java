package repository;

import entity.Leagues;
import entity.Statistics;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StatisticRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertNewStatistic(Statistics statistic) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(statistic);
        em.getTransaction().commit();
        em.close();
    }


    public void updateNewStatistic(Statistics statistic){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(statistic);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteStatistic(Statistics statistic){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(statistic) ? statistic : em.merge(statistic));
        em.getTransaction().commit();
        em.close();
    }

    public Statistics findStatisticById(String id_statistics) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Statistics statistics = em.find(Statistics.class,id_statistics);
        em.close();
        return statistics;
    }


}
