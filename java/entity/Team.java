package entity;
import dto.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="teams")
public class Team {
    @Id
    private String id_team;

    @Column
    private String id_followers;
    @Column
    private String name;
    @Column
    private int team_rank;
    @Column
    private int matches_played;
    @Column
    private int wins;
    @Column
    private int draws;
    @Column
    private int losses;
    @Column
    private String goals;
    @Column
    private int points;
    @Column
    private String coach;

    @ManyToOne
    @JoinColumn(name="id_league")
    private Leagues league;

    public Leagues getLeagues() {
        return league;
    }

    public void setLeagues(Leagues leagues) {
        this.league = leagues;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "team",orphanRemoval = true,cascade = CascadeType.PERSIST)
    private Set<Player> players;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "team1",orphanRemoval = true,cascade = CascadeType.PERSIST)
    private Set<Match> hostMatches;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "team2",orphanRemoval = true,cascade = CascadeType.PERSIST)
    private Set<Match> guestMatches;

    public Set<Match> getHostMatches() {
        return hostMatches;
    }

    public void setHostMatches(Set<Match> hostMatches) {
        this.hostMatches = hostMatches;
    }

    public Set<Match> getGuestMatches() {
        return guestMatches;
    }

    public void setGuestMatches(Set<Match> guestMatches) {
        this.guestMatches = guestMatches;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Team(String name) {
        this.name = name;
    }

    public Team(){}
    public String getId_team() {
        return id_team;
    }

    public void setId_team(String id_team) {
        this.id_team = id_team;
    }

    public String getId_followers() {
        return id_followers;
    }

    public void setId_followers(String id_followers) {
        this.id_followers = id_followers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return team_rank;
    }

    public void setRank(int rank) {
        this.team_rank = rank;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

}
