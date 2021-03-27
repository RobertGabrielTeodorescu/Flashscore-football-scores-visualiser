package controller;

import dto.LineupDto;
import dto.MatchDto;
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
import service.LineupService;
import service.PlayerService;
import service.TeamService;

import java.io.IOException;
import java.util.List;

public class MatchLineupsController {
    private MatchDto matchDto;

    @FXML private ImageView teamLogo1;
    @FXML private ImageView teamLogo2;
    @FXML private Label teamName1;
    @FXML private Label teamName2;
    @FXML private Label score;
    @FXML private Label time;
    @FXML private Label formation1;
    @FXML private Label formation2;
    @FXML private Label starting_players1;
    @FXML private Label starting_players2;
    @FXML private Label substitutes1;
    @FXML private Label substitutes2;
    @FXML private Label coach1;
    @FXML private Label coach2;

    public void initialize(MatchDto matchDto){
        this.matchDto = matchDto;
        TeamService teamService = new TeamService();
        LineupService lineupService = new LineupService();
        PlayerService playerService = new PlayerService();
        List<LineupDto> lineups;

        teamName1.setText(matchDto.getTeam1().getName());
        teamName2.setText(matchDto.getTeam2().getName());
        teamLogo1.setImage(new Image("/team_logos/" + matchDto.getTeam1().getName() + ".png"));
        teamLogo2.setImage(new Image("/team_logos/" + matchDto.getTeam2().getName()  + ".png"));

        score.setText(matchDto.getResult());

        String timeStr = matchDto.getTime().split("@")[2];
        if(timeStr.equals("Not Started")){
            timeStr = matchDto.getTime().split("@")[0] + " " + matchDto.getTime().split("@")[1];
        }
        time.setText(timeStr);


        lineups = lineupService.getLineups(matchDto.getId_match());

        LineupDto lineup1 = lineups.get(0);
        LineupDto lineup2 = lineups.get(1);

        if(!lineup1.getId_team().equals(matchDto.getTeam1().getId_team())){
            LineupDto aux = lineup1;
            lineup1 = lineup2;
            lineup2 = aux;
        }

        formation1.setText(lineup1.getFormation());
        formation2.setText(lineup2.getFormation());

        starting_players1.setText(lineupService.getPlayersData1(lineup1.getStarting_players_ids(), playerService));
        starting_players2.setText(lineupService.getPlayersData2(lineup2.getStarting_players_ids(), playerService));
        substitutes1.setText(lineupService.getPlayersData1(lineup1.getSubstitutes_ids(), playerService));
        substitutes2.setText(lineupService.getPlayersData2(lineup2.getSubstitutes_ids(), playerService));

        coach1.setText(matchDto.getTeam1().getCoach());
        coach2.setText(matchDto.getTeam2().getCoach());
    }

    public void changeToMatchSummaryFrame(ActionEvent event) throws IOException {
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

    public void changeToMatchStatisticsFrame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/match-statistics-frame.fxml"));
        Parent viewParent = loader.load();
        Scene viewScene = new Scene(viewParent);
        MatchStatisticsController matchStatisticsController = loader.getController();
        matchStatisticsController.initialize(matchDto);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        String application_title = teamName1.getText() + " - " + teamName2.getText() + " | Statistics";
        window.setTitle(application_title);
        window.show();
    }
}
