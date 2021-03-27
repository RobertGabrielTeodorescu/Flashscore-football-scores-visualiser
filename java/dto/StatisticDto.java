package dto;

import entity.Match;

import javax.persistence.Column;
import javax.persistence.Id;

public class StatisticDto {

    private String id_statistics;

    private String ball_possession;

    private String goal_attempts;

    private String shots_on_goal;

    private String shots_off_goal;

    private String blocked_shots;

    private String free_kicks;

    private String corner_kicks;

    private String offsides;

    private String foults;

    private String yellow_cards;

    private String red_cards;

    private String total_passes;

    private Match match;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getId_statistics() {
        return id_statistics;
    }

    public  void setId_statistics(String id_statistics) {
        this.id_statistics = id_statistics;
    }

    public String getBall_possession() {
        return ball_possession;
    }

    public void setBall_possession(String ball_possession) {
        this.ball_possession = ball_possession;
    }

    public String getGoal_attempts() {
        return goal_attempts;
    }

    public void setGoal_attempts(String goal_attempts) {
        this.goal_attempts = goal_attempts;
    }

    public String getShots_on_goal() {
        return shots_on_goal;
    }

    public void setShots_on_goal(String shots_on_goal) {
        this.shots_on_goal = shots_on_goal;
    }

    public String getShots_off_goal() {
        return shots_off_goal;
    }

    public void setShots_off_goal(String shots_off_goal) {
        this.shots_off_goal = shots_off_goal;
    }

    public String getBlocked_shots() {
        return blocked_shots;
    }

    public void setBlocked_shots(String blocked_shots) {
        this.blocked_shots = blocked_shots;
    }

    public String getFree_kicks() {
        return free_kicks;
    }

    public void setFree_kicks(String free_kicks) {
        this.free_kicks = free_kicks;
    }

    public String getCorner_kicks() {
        return corner_kicks;
    }

    public void setCorner_kicks(String corner_kicks) {
        this.corner_kicks = corner_kicks;
    }

    public String getOffsides() {
        return offsides;
    }

    public void setOffsides(String offsides) {
        this.offsides = offsides;
    }

    public String getFoults() {
        return foults;
    }

    public void setFoults(String foults) {
        this.foults = foults;
    }

    public String getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(String yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public String getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(String red_cards) {
        this.red_cards = red_cards;
    }

    public String getTotal_passes() {
        return total_passes;
    }

    public void setTotal_passes(String total_passes) {
        this.total_passes = total_passes;
    }
}
