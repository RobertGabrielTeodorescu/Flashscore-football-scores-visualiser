package repository;

import dto.MatchDto;
import dto.TeamDto;
import entity.Match;
import entity.Team;
import querries.MatchQuerries;
import utils.ApplicationUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MatchRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertMatch(Match match){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(match);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Match findMatch(String id_match){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Match match = entityManager.find(Match.class, id_match);
        entityManager.getTransaction().commit();
        entityManager.close();
        return match;
    }

    public void deleteMatch(Match match){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(match) ? match : entityManager.merge(match));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateMatch(Match match){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(match);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<Match> findResults(Team team){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Set<Match> allMatches=team.getHostMatches();
        allMatches.addAll(team.getGuestMatches());
        entityManager.close();
        List<Match> fixtures=new ArrayList<>();
        for(Match match:allMatches){
            if(!match.getResult().equals("-")){
                fixtures.add(match);
            }
        }
        return fixtures;
    }

    public List<Match> findFixtures(Team team){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Set<Match> allMatches=team.getHostMatches();
        allMatches.addAll(team.getGuestMatches());
        entityManager.close();
        List<Match> fixtures=new ArrayList<>();
        for(Match match:allMatches){
            if(match.getResult().equals("-")){
                fixtures.add(match);
            }
        }
        return fixtures;
    }

    public List<Match> getMatches() {
        List<Match> allMatches;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        TypedQuery<Match> query = entityManager.createQuery(MatchQuerries.FIND_ALL_MATCHES, Match.class);
        allMatches = query.getResultList();
        entityManager.close();

        return allMatches;
    }

    public List<Match> findFixturesByLeague(String id_league){
        List<Match> allMatches=getMatches();
        List<Match> fixtures=new ArrayList<>();
        for(Match match:allMatches){
            if(match.getTeam1().getLeagues().getId_league().equals(id_league)&&match.getResult().equals("-")){
                fixtures.add(match);
            }
        }
        return fixtures;
    }

    public List<Match> findResultsByLeague(String id_league){
        List<Match> allMatches=getMatches();
        List<Match> results=new ArrayList<>();
        for(Match match:allMatches){
            if(match.getTeam1().getLeagues().getId_league().equals(id_league)&&!match.getResult().equals("-")){
                results.add(match);
            }
        }
        return results;
    }

}
