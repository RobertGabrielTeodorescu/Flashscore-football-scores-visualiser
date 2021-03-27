package entity;

import javax.persistence.*;

@Entity
@Table(name = "lineups")

public class Lineup {

    @Id
    private String id_lineup;

    @Column
    private String id_match;
    private String id_team;
    private String formation;
    private String starting_players_ids;
    private String substitutes_ids;

    public String getId_lineup() {
        return id_lineup;
    }

    public void setId_lineup(String id_lineup) {
        this.id_lineup = id_lineup;
    }

    public String getId_match() {
        return id_match;
    }

    public void setId_match(String id_match) {
        this.id_match = id_match;
    }

    public String getId_team() {
        return id_team;
    }

    public void setId_team(String id_team) {
        this.id_team = id_team;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getStarting_players_ids() {
        return starting_players_ids;
    }

    public void setStarting_players_ids(String starting_players_ids) {
        this.starting_players_ids = starting_players_ids;
    }

    public String getSubstitutes_ids() {
        return substitutes_ids;
    }

    public void setSubstitutes_ids(String substitutes_ids) {
        this.substitutes_ids = substitutes_ids;
    }
}
