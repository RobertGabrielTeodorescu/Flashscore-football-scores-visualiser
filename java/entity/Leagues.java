package entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "leagues")

public class Leagues {
    @Id
    private String id_league;

    @Column
    private String name;

    @Column
    private int number_of_teams;

    @Column
    private int current_fixture;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "league",orphanRemoval = true,cascade = CascadeType.PERSIST)
    private Set<Team> teams;

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Leagues() {};

    public String getId_league() {
        return id_league;
    }

    public void setId_league(String id_league) {
        this.id_league = id_league;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_teams() {
        return number_of_teams;
    }

    public void setNumber_of_teams(int number_of_teams) {
        this.number_of_teams = number_of_teams;
    }

    public int getCurrent_fixture() {
        return current_fixture;
    }

    public void setCurrent_fixture(int current_fixture) {
        this.current_fixture = current_fixture;
    }
}
