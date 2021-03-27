package dto;

import entity.Leagues;
import entity.Match;
import entity.Player;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.Set;

public class TeamDto {
    private String id_team;
    private String id_league;
    private ArrayList<String> id_followers;
    private SimpleStringProperty name;
    private SimpleStringProperty league_name;
    private int rank;
    private int matches_played;
    private int wins;
    private int draws;
    private int losses;
    private SimpleStringProperty goals;
    private int points;
    private SimpleStringProperty coach;

    private Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    private Leagues league;

    public Leagues getLeagues() {
        return league;
    }

    public void setLeagues(Leagues leagues) {
        this.league = leagues;
    }

    public String getLeague_name() {
        return league_name.get();
    }

    public SimpleStringProperty league_nameProperty() {
        return league_name;
    }

    public void setLeague_name(String league_name) {
        this.league_name=new SimpleStringProperty(league_name);
    }

    public String getId_team() {
        return id_team;
    }

    public void setId_team(String id_team) {
        this.id_team = id_team;
    }

    public String getId_league() {
        return id_league;
    }

    public void setId_league(String id_league) {
        this.id_league = id_league;
    }

    public ArrayList<String> getId_followers() {
        return id_followers;
    }

    public void setId_followers(ArrayList<String> id_followers) {
        this.id_followers = id_followers;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name=new SimpleStringProperty(name);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int team_rank) {
        this.rank = team_rank;
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
        return goals.get();
    }

    public SimpleStringProperty goalsProperty() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals=new SimpleStringProperty(goals);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getCoach() {
        return coach.get();
    }

    public SimpleStringProperty coachProperty() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach=new SimpleStringProperty(coach);
    }

    private Set<Match> hostMatches;
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

}
