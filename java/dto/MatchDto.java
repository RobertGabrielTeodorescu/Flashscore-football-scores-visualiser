package dto;

import entity.Statistics;
import entity.Team;
import javafx.beans.property.SimpleStringProperty;
import repository.MatchRepo;
import service.MatchService;
import service.TeamService;
import utils.ApplicationUtils;

import java.util.List;

public class MatchDto {

    private String id_match;
    private String id_statistics;
    private String id_team1;
    private String id_team2;
    private String time;
    private String stadium;
    private String referee;
    private String result;

    private SimpleStringProperty date;
    private SimpleStringProperty hour;
    private SimpleStringProperty hosts;
    private SimpleStringProperty score;
    private SimpleStringProperty guests;
    private SimpleStringProperty minute;

    private Team team1;
    private Team team2;

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    private Statistics statistics;

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public MatchDto(){}


    public String getMinute() {
        return minute.get();
    }

    public SimpleStringProperty minuteProperty() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute=new SimpleStringProperty(ApplicationUtils.stringToArraylist(time,"@").get(2));
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String minute) {
        this.date=new SimpleStringProperty(ApplicationUtils.stringToArraylist(time,"@").get(0));
    }

    public String getHour() {
        return hour.get();
    }

    public SimpleStringProperty hourProperty() {
        return hour;
    }

    public void setHour(String time) {
        this.hour=new SimpleStringProperty(ApplicationUtils.stringToArraylist(time,"@").get(1));
    }

    public String getHosts() {
        return hosts.get();
    }

    public SimpleStringProperty hostsProperty() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts=new SimpleStringProperty(hosts);
    }

    public String getScore() {
        return score.get();
    }

    public SimpleStringProperty scoreProperty() {
        return score;
    }

    public void setScore(String score) {
        this.score=new SimpleStringProperty(score);
    }

    public String getGuests() {
        return guests.get();
    }

    public SimpleStringProperty guestsProperty() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests=new SimpleStringProperty(guests);
    }

    public String getId_match() {
        return id_match;
    }

    public void setId_match(String id_match) {
        this.id_match = id_match;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
