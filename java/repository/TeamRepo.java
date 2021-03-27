package repository;

import entity.Match;
import entity.Player;
import entity.Team;
import entity.User;
import querries.PlayerQuerries;
import querries.TeamQuerries;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertNewTeam(Team team) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(team);
        em.getTransaction().commit();
        em.close();
    }

    public void updateTeam(Team team){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(team);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteTeam(Team team){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(team) ? team : em.merge(team));
        em.getTransaction().commit();
        em.close();
    }

    public Team findTeam(String id_team){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Team team = entityManager.find(Team.class, id_team);
        entityManager.getTransaction().commit();
        entityManager.close();
        return team;
    }

    public List<Team> findTeamsByLeague(String id_league){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Team> query=em.createQuery(TeamQuerries.FIND_TEAMS_BY_LEAGUE,Team.class);
        query.setParameter("league_id",id_league);
        List<Team> teams= query.getResultList();
        em.close();
        return teams;
    }

    public Team findTeamByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(TeamQuerries.FIND_TEAM_BY_NAME);
        query.setParameter("team_name",name);
        Team team= (Team) query.getResultList().get(0);
        em.close();
        return team;
    }

    public List<Team> findAllTeams(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Team> query=em.createQuery(TeamQuerries.FIND_ALL_TEAMS,Team.class);
        List<Team> teams= query.getResultList();
        em.close();
        return teams;
    }

}
