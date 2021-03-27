package controller;

import dto.MatchDto;
import dto.StatisticDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mappers.StatisticMapper;
import service.StatisticService;
import service.TeamService;

import java.io.IOException;

public class MatchStatisticsController {

    private MatchDto matchDto;

    @FXML
    private ImageView teamLogo1;
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
        TeamService teamService = new TeamService();
        StatisticService statisticService = new StatisticService();

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

        StatisticDto statisticDto = StatisticMapper.entityToDtoS(matchDto.getStatistics());
        setStatistics(statisticDto);
    }

    private void setStatistics(StatisticDto statisticDto){
        String[] ball_possession = statisticDto.getBall_possession().split("-");
        ball_possession1.setText(ball_possession[0] + "%");
        ball_possession2.setText(ball_possession[1] + "%");

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

    public void changeToMatchSummaryFrame(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/match-summary-frame.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene = new Scene(viewParent);
        MatchSummaryController matchSummaryController = loader.getController();
        matchSummaryController.initialize(matchDto);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        String application_title = teamName1.getText() + " - " + teamName2.getText() + " | Match Summary";
        window.setTitle(application_title);
        window.show();
    }

    public void changeToMatchLineupsFrame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/match-lineups-frame.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene = new Scene(viewParent);
        MatchLineupsController matchLineupsController = loader.getController();
        matchLineupsController.initialize(matchDto);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        String application_title = teamName1.getText() + " - " + teamName2.getText() + " | Lineups";
        window.setTitle(application_title);
        window.show();
    }
}
