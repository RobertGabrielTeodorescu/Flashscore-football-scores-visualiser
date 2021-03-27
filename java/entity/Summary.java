package entity;

import javax.persistence.*;

@Entity
@Table(name="summary")
public class Summary {
    @Id
    private String id_summary;
    @Column
    private String id_match;
    @Column
    private String id_team;
    @Column
    private String id_player1;
    @Column
    private String id_player2;
    @Column
    private String minute;
    @Column
    private String type;
    @Column
    private String penalty;

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public Summary(String id_match) {
        this.id_match = id_match;
    }
    public Summary(){}

    public String getId_summary() {
        return id_summary;
    }

    public void setId_summary(String id_summary) {
        this.id_summary = id_summary;
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

    public String getId_player1() {
        return id_player1;
    }

    public void setId_player1(String id_player1) {
        this.id_player1 = id_player1;
    }

    public String getId_player2() {
        return id_player2;
    }

    public void setId_player2(String id_player2) {
        this.id_player2 = id_player2;
    }


}
