package controller;

import dto.MatchDto;
import dto.StatisticDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mappers.StatisticMapper;
import service.StatisticService;
import service.TeamService;

public class AdminStatisticsController{

    private Alert alert=new Alert(Alert.AlertType.NONE);

    private StatisticService statisticService;
    private StatisticDto statisticDto;
    private MatchDto matchDto;

    @FXML private ImageView teamLogo1;
    @FXML private ImageView teamLogo2;
    @FXML private Label teamName1;
    @FXML private Label teamName2;
    @FXML private Label score;
    @FXML private Label time;

    @FXML private Label ball_possession1;
    @FXML private Label ball_possession2;
    @FXML private Label goal_attempts1;
    @FXML private Label goal_attempts2;
    @FXML private Label shots_on_goal1;
    @FXML private Label shots_on_goal2;
    @FXML private Label shots_off_goal1;
    @FXML private Label shots_off_goal2;
    @FXML private Label blocked_shots1;
    @FXML private Label blocked_shots2;
    @FXML private Label free_kicks1;
    @FXML private Label free_kicks2;
    @FXML private Label corner_kicks1;
    @FXML private Label corner_kicks2;
    @FXML private Label offsides1;
    @FXML private Label offsides2;
    @FXML private Label fouls1;
    @FXML private Label fouls2;
    @FXML private Label yellow_cards1;
    @FXML private Label yellow_cards2;
    @FXML private Label red_cards1;
    @FXML private Label red_cards2;
    @FXML private Label total_passes1;
    @FXML private Label total_passes2;


    public void initialize(MatchDto matchDto){
        this.matchDto = matchDto;
        TeamService teamService =  new TeamService();

        teamName1.setText(matchDto.getTeam1().getName());
        teamName2.setText(matchDto.getTeam2().getName());
        teamLogo1.setImage(new Image("/team_logos/" + matchDto.getTeam1().getName() + ".png"));
        teamLogo2.setImage(new Image("/team_logos/" + matchDto.getTeam2().getName() + ".png"));

        score.setText(matchDto.getResult());

        String timeStr = matchDto.getTime().split("@")[2];
        if(timeStr.equals("Not Started")){
            timeStr = matchDto.getTime().split("@")[0] + " " + matchDto.getTime().split("@")[1];
        }
        time.setText(timeStr);

        statisticService = new StatisticService();
        this.statisticDto = StatisticMapper.entityToDtoS(matchDto.getStatistics());
        setStatistics(statisticDto);
    }

    private void setStatistics(StatisticDto statisticDto){
        String[] ball_possession = statisticDto.getBall_possession().split("-");
        ball_possession1.setText(ball_possession[0]);
        ball_possession2.setText(ball_possession[1]);

        String[] goal_attempts = statisticDto.getGoal_attempts().split("-");
        goal_attempts1.setText(goal_attempts[0]);
        goal_attempts2.setText(goal_attempts[1]);

        String[] shots_on_goal = statisticDto.getShots_on_goal().split("-");
        shots_on_goal1.setText(shots_on_goal[0]);
        shots_on_goal2.setText(shots_on_goal[1]);

        String[] shots_off_goal = statisticDto.getShots_off_goal().split("-");
        shots_off_goal1.setText(shots_off_goal[0]);
        shots_off_goal2.setText(shots_off_goal[1]);

        String[] blocked_shots = statisticDto.getBlocked_shots().split("-");
        blocked_shots1.setText(blocked_shots[0]);
        blocked_shots2.setText(blocked_shots[1]);

        String[] free_kicks = statisticDto.getFree_kicks().split("-");
        free_kicks1.setText(free_kicks[0]);
        free_kicks2.setText(free_kicks[1]);

        String[] corner_kicks = statisticDto.getCorner_kicks().split("-");
        corner_kicks1.setText(corner_kicks[0]);
        corner_kicks2.setText(corner_kicks[1]);

        String[] offsides = statisticDto.getOffsides().split("-");
        offsides1.setText(offsides[0]);
        offsides2.setText(offsides[1]);

        String[] fouls = statisticDto.getFoults().split("-");
        fouls1.setText(fouls[0]);
        fouls2.setText(fouls[1]);

        String[] yellow_cards = statisticDto.getYellow_cards().split("-");
        yellow_cards1.setText(yellow_cards[0]);
        yellow_cards2.setText(yellow_cards[1]);

        String[] red_cards = statisticDto.getRed_cards().split("-");
        red_cards1.setText(red_cards[0]);
        red_cards2.setText(red_cards[1]);

        String[] total_passes = statisticDto.getTotal_passes().split("-");
        total_passes1.setText(total_passes[0]);
        total_passes2.setText(total_passes[1]);
    }

    private void increment(Label label){
        String new_data = String.valueOf(Integer.parseInt(label.getText()) + 1);
        label.setText(new_data);
    }

    private void decrement(Label label){
        int new_data_int = Integer.parseInt(label.getText()) - 1;
        if(new_data_int < 0){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("This number must be greater or equal than 0.");
            alert.show();
        }
        else {
            label.setText(String.valueOf(new_data_int));
        }
    }

    // BALL POSSESSION

    private void incrementBallPossession(Label label){
        int new_ball_possession = Integer.parseInt(label.getText()) + 1;
        if(new_ball_possession > 100){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("This number must be less or equal than 100.");
            alert.show();
        }
        else{
            label.setText(String.valueOf(new_ball_possession));
        }
    }

    private void updateBallPossession(Label ball_possession1, Label ball_possession2){
            statisticDto.setBall_possession(ball_possession1.getText() + "-" + ball_possession2.getText());
            statisticService.updateStatistic(statisticDto);
    }

    public void minusBallPossession(ActionEvent actionEvent){
        decrement(ball_possession1);
        incrementBallPossession(ball_possession2);
        updateBallPossession(ball_possession1, ball_possession2);
    }

    public void plusBallPossession(ActionEvent actionEvent){
        incrementBallPossession(ball_possession1);
        decrement(ball_possession2);
        updateBallPossession(ball_possession1, ball_possession2);
    }

    // GOAL ATTEMPTS

    private void updateGoalAttempts(Label goal_attempts1, Label goal_attempts2){
        statisticDto.setGoal_attempts(goal_attempts1.getText() + "-" + goal_attempts2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusGoalAttempts1(ActionEvent actionEvent){
        decrement(goal_attempts1);
        updateGoalAttempts(goal_attempts1, goal_attempts2);
    }

    public void plusGoalAttempts1(ActionEvent actionEvent){
        increment(goal_attempts1);
        updateGoalAttempts(goal_attempts1, goal_attempts2);
    }

    public void minusGoalAttempts2(ActionEvent actionEvent){
        decrement(goal_attempts2);
        updateGoalAttempts(goal_attempts1, goal_attempts2);
    }

    public void plusGoalAttempts2(ActionEvent actionEvent){
        increment(goal_attempts2);
        updateGoalAttempts(goal_attempts1, goal_attempts2);
    }

    // SHOTS ON GOAL

    private void updateShotsOnGoal(Label shots_on_goal1, Label shots_on_goal2){
        statisticDto.setShots_on_goal(shots_on_goal1.getText() + "-" + shots_on_goal2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusShotsOnGoal1(ActionEvent actionEvent){
        decrement(shots_on_goal1);
        updateShotsOnGoal(shots_on_goal1, shots_on_goal2);
    }

    public void plusShotsOnGoal1(ActionEvent actionEvent){
        increment(shots_on_goal1);
        updateShotsOnGoal(shots_on_goal1, shots_on_goal2);
    }

    public void minusShotsOnGoal2(ActionEvent actionEvent) {
        decrement(shots_on_goal2);
        updateShotsOnGoal(shots_on_goal1, shots_on_goal2);
    }

    public void plusShotsOnGoal2(ActionEvent actionEvent){
        increment(shots_on_goal2);
        updateShotsOnGoal(shots_on_goal1, shots_on_goal2);
    }

    // SHOTS OFF GOAL

    private void updateShotsOffGoal(Label shots_off_goal1, Label shots_off_goal2){
        statisticDto.setShots_off_goal(shots_off_goal1.getText() + "-" + shots_off_goal2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusShotsOffGoal1(ActionEvent actionEvent){
        decrement(shots_off_goal1);
        updateShotsOffGoal(shots_off_goal1, shots_off_goal2);
    }

    public void plusShotsOffGoal1(ActionEvent actionEvent){
        increment(shots_off_goal1);
        updateShotsOffGoal(shots_off_goal1, shots_off_goal2);
    }

    public void minusShotsOffGoal2(ActionEvent actionEvent){
        decrement(shots_off_goal2);
        updateShotsOffGoal(shots_off_goal1, shots_off_goal2);
    }

    public void plusShotsOffGoal2(ActionEvent actionEvent){
        increment(shots_off_goal2);
        updateShotsOffGoal(shots_off_goal1, shots_off_goal2);
    }

    // BLOCKED SHOTS

    private void updateBlockedShots(Label blocked_shots1, Label blocked_shots2){
        statisticDto.setBlocked_shots(blocked_shots1.getText() + "-" + blocked_shots2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusBlockedShots1(ActionEvent actionEvent){
        decrement(blocked_shots1);
        updateBlockedShots(blocked_shots1, blocked_shots2);
    }

    public void plusBlockedShots1(ActionEvent actionEvent){
        increment(blocked_shots1);
        updateBlockedShots(blocked_shots1, blocked_shots2);
    }

    public void minusBlockedShots2(ActionEvent actionEvent){
        decrement(blocked_shots2);
        updateBlockedShots(blocked_shots1, blocked_shots2);
    }

    public void plusBlockedShots2(ActionEvent actionEvent){
        increment(blocked_shots2);
        updateBlockedShots(blocked_shots1, blocked_shots2);
    }

    // FREE KICKS

    private void updateFreeKicks(Label free_kicks1, Label free_kicks2){
        statisticDto.setFree_kicks(free_kicks1.getText() + "-" + free_kicks2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusFreeKicks1(ActionEvent actionEvent){
        decrement(free_kicks1);
        updateFreeKicks(free_kicks1, free_kicks2);
    }

    public void plusFreeKicks1(ActionEvent actionEvent){
        increment(free_kicks1);
        updateFreeKicks(free_kicks1, free_kicks2);
    }

    public void minusFreeKicks2(ActionEvent actionEvent){
        decrement(free_kicks2);
        updateFreeKicks(free_kicks1, free_kicks2);
    }

    public void plusFreeKicks2(ActionEvent actionEvent){
        increment(free_kicks2);
        updateFreeKicks(free_kicks1, free_kicks2);
    }

    // CORNER KICKS

    private void updateCornerKicks(Label corner_kicks1, Label corner_kicks2){
        statisticDto.setCorner_kicks(free_kicks1.getText() + "-" + free_kicks2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusCornerKicks1(ActionEvent actionEvent){
        decrement(corner_kicks1);
        updateCornerKicks(corner_kicks1, corner_kicks2);
    }

    public void plusCornerKicks1(ActionEvent actionEvent){
        increment(corner_kicks1);
        updateCornerKicks(corner_kicks1, corner_kicks2);
    }

    public void minusCornerKicks2(ActionEvent actionEvent){
        decrement(corner_kicks2);
        updateCornerKicks(corner_kicks1, corner_kicks2);
    }

    public void plusCornerKicks2(ActionEvent actionEvent){
        increment(corner_kicks2);
        updateCornerKicks(corner_kicks1, corner_kicks2);;
    }

    // OFFSIDES

    private void updateOffsides(Label offsides1, Label offsides2){
        statisticDto.setOffsides(offsides1.getText() + "-" + offsides2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusOffsides1(ActionEvent actionEvent){
        decrement(offsides1);
        updateOffsides(offsides1, offsides2);
    }

    public void plusOffsides1(ActionEvent actionEvent){
        increment(offsides1);
        updateOffsides(offsides1, offsides2);
    }

    public void minusOffsides2(ActionEvent actionEvent){
        decrement(offsides2);
        updateOffsides(offsides1, offsides2);
    }

    public void plusOffsides2(ActionEvent actionEvent){
        increment(offsides2);
        updateOffsides(offsides1, offsides2);
    }

    // FOULS

    private void updateFouls(Label fouls1, Label fouls2){
        statisticDto.setFoults(fouls1.getText() + "-" + fouls2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusFouls1(ActionEvent actionEvent){
        decrement(fouls1);
        updateFouls(fouls1, fouls2);
    }

    public void plusFouls1(ActionEvent actionEvent){
        increment(fouls1);
        updateFouls(fouls1, fouls2);
    }

    public void minusFouls2(ActionEvent actionEvent){
        decrement(fouls2);
        updateFouls(fouls1, fouls2);
    }

    public void plusFouls2(ActionEvent actionEvent){
        increment(fouls2);
        updateFouls(fouls1, fouls2);
    }

    // YELLOW CARDS

    private void updateYellowCards(Label yellow_cards1, Label yellow_cards2){
        statisticDto.setYellow_cards(yellow_cards1.getText() + "-" + yellow_cards2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusYellowCards1(ActionEvent actionEvent){
        decrement(yellow_cards1);
        updateYellowCards(yellow_cards1, yellow_cards2);
    }

    public void plusYellowCards1(ActionEvent actionEvent){
        increment(yellow_cards1);
        updateYellowCards(yellow_cards1, yellow_cards2);
    }

    public void minusYellowCards2(ActionEvent actionEvent){
        decrement(yellow_cards2);
        updateYellowCards(yellow_cards1, yellow_cards2);
    }

    public void plusYellowCards2(ActionEvent actionEvent){
        increment(yellow_cards2);
        updateYellowCards(yellow_cards1, yellow_cards2);
    }

    // RED CARDS

    private void updateRedCards(Label red_cards1, Label red_cards2){
        statisticDto.setRed_cards(red_cards1.getText() + "-" + red_cards2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusRedCards1(ActionEvent actionEvent){
        decrement(red_cards1);
        updateRedCards(red_cards1, red_cards2);
    }

    public void plusRedCards1(ActionEvent actionEvent){
        increment(red_cards1);
        updateRedCards(red_cards1, red_cards2);
    }

    public void minusRedCards2(ActionEvent actionEvent){
        decrement(red_cards2);
        updateRedCards(red_cards1, red_cards2);
    }

    public void plusRedCards2(ActionEvent actionEvent){
        increment(red_cards2);
        updateRedCards(red_cards1, red_cards2);
    }

    // TOTAL PASSES

    private void updateTotalPasses(Label total_passes1, Label total_passes2){
        statisticDto.setTotal_passes(total_passes1.getText() + "-" + total_passes2.getText());
        statisticService.updateStatistic(statisticDto);
    }

    public void minusTotalPasses1(ActionEvent actionEvent){
        decrement(total_passes1);
        updateTotalPasses(total_passes1, total_passes2);
    }

    public void plusTotalPasses1(ActionEvent actionEvent){
        increment(total_passes1);
        updateTotalPasses(total_passes1, total_passes2);
    }

    public void minusTotalPasses2(ActionEvent actionEvent){
        decrement(total_passes2);
        updateTotalPasses(total_passes1, total_passes2);
    }

    public void plusTotalPasses2(ActionEvent actionEvent){
        increment(total_passes2);
        updateTotalPasses(total_passes1, total_passes2);
    }
}
