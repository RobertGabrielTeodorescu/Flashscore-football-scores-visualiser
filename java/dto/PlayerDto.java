package dto;

import entity.Team;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Column;
import javax.persistence.Id;

public class PlayerDto {

    private String id_player;

    private String id_team;

    private SimpleStringProperty name;

    private SimpleStringProperty nationality;

    private int age;

    private SimpleStringProperty position;

    private int jersey_number;

    private int matches_played;

    private int goals_scored;

    private int yellow_cards;

    private int red_cards;

    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    private SimpleStringProperty teamName;

    private SimpleStringProperty leagueName;

    public String getNationality() {
        return nationality.get();
    }

    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality=new SimpleStringProperty(nationality);
    }

    public String getLeagueName() {
        return leagueName.get();
    }

    public SimpleStringProperty leagueNameProperty() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName=new SimpleStringProperty(leagueName);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName=new SimpleStringProperty(teamName);
    }

    public PlayerDto(){}


    public String getId_player() {
        return id_player;
    }

    public void setId_player(String id_player) {
        this.id_player = id_player;
    }

    public String getId_team() {
        return id_team;
    }

    public void setId_team(String id_team) {
        this.id_team = id_team;
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


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position.get();
    }

    public SimpleStringProperty positionProperty() {
        return position;
    }

    public void setPosition(String position) {
        this.position=new SimpleStringProperty(position);
    }

    public int getJersey_number() {
        return jersey_number;
    }

    public void setJersey_number(int jersey_number) {
        this.jersey_number = jersey_number;
    }

    public int getMatches_played() {
        return matches_played;
    }

    public void setMatches_played(int matches_played) {
        this.matches_played = matches_played;
    }

    public int getGoals_scored() {
        return goals_scored;
    }

    public void setGoals_scored(int goals_scored) {
        this.goals_scored = goals_scored;
    }

    public int getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(int yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public int getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(int red_cards) {
        this.red_cards = red_cards;
    }
}
