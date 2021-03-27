package entity;


import javax.persistence.*;

@Entity
@Table(name="players")
public class Player {

    @Id
    private String id_player;

    @Column
    private String name;

    @Column
    private String nationality;

    @Column
    private int age;

    @Column
    private String position;

    @Column
    private int jersey_number;

    @Column
    private int matches_played;

    @Column
    private int goals_scored;

    @Column
    private int yellow_cards;

    @Column
    private int red_cards;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getId_player() {
        return id_player;
    }

    public void setId_player(String id_player) {
        this.id_player = id_player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public void setMatches_played(int mathces_played) {
        this.matches_played = mathces_played;
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
