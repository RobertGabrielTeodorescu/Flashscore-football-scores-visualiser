package repository;

import entity.Player;
import entity.Team;
import entity.User;
import querries.PlayerQuerries;
import querries.UserQuerries;

import javax.persistence.*;
import java.util.List;

public class PlayerRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");

    public void insertNewPlayer(Player player) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();
    }

    public void updatePlayer(Player player){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(player);
        em.getTransaction().commit();
        em.close();
    }

    public void deletePlayer(Player player){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(player) ? player : em.merge(player));
        em.getTransaction().commit();
        em.close();
    }

    public Player findPlayerByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(PlayerQuerries.FIND_PLAYER_BY_NAME);
        query.setParameter("player_name",name);
        Player player= (Player) query.getResultList().get(0);
        em.close();
        return player;
    }

    public Player findPlayerById(String id){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query=em.createQuery(PlayerQuerries.FIND_PLAYER_BY_ID);
        query.setParameter("player_id",id);
        Player player= (Player) query.getResultList().get(0);
        em.getTransaction().commit();
        em.close();
        return player;
    }

    public List<Player> findPlayersByTeam(Team team){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Player> query=em.createQuery(PlayerQuerries.FIND_PLAYERS_BY_TEAM,Player.class);
        query.setParameter("team_id",team.getId_team());
        List<Player> players= query.getResultList();
        em.getTransaction().commit();
        em.close();
        return players;
    }

    public List<Player> findAllPlayers(){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Player> query=em.createQuery(PlayerQuerries.FIND_ALL_PLAYERS,Player.class);
        List<Player> players= query.getResultList();
        em.close();
        return players;
    }

}
