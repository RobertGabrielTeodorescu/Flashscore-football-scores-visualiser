package dto;

public class SummaryDto {
    private String id_summary;
    private String id_match;
    private String id_team;
    private String id_player1;
    private String id_player2;
    private String minute;
    private String type;
    private String penalty;
    public String getId_summary() {
        return id_summary;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
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



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
