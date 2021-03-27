package repository;

import entity.Leagues;
import entity.Team;
import entity.User;
import querries.LeagueQuerries;
import querries.TeamQuerries;

import javax.persistence.*;
import java.util.List;

public class LeagueRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertNewLeague(Leagues league) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(league);
        em.getTransaction().commit();
        em.close();
    }

    public void updateLeague(Leagues league){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(league);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteLeague(Leagues league){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(league) ? league : em.merge(league));
        em.getTransaction().commit();
        em.close();
    }

    public Leagues findLeagueById(String id_league) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Leagues league = em.find(Leagues.class,id_league);
        em.getTransaction().commit();
        em.close();
        return league;
    }

    public List<Leagues> findAllLeagues(){
        EntityManager em=entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Leagues> query=em.createQuery(LeagueQuerries.FIND_ALL_LEAGUES,Leagues.class);
        List<Leagues> leagues= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return leagues;
    }

    public List<Team> findTeamsOfLeague(String id_league){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Team> query=em.createQuery(TeamQuerries.FIND_TEAMS_BY_LEAGUE,Team.class);
        query.setParameter("league_id",id_league);
        List<Team> teams= query.getResultList();
        em.close();
        return teams;
    }

    public Leagues findLeagueByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(LeagueQuerries.FIND_LEAGUE_BY_NAME);
        query.setParameter("league_name",name);
        Leagues league= (Leagues) query.getResultList().get(0);
        em.close();
        return league;
    }

}
