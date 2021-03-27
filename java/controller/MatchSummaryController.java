package controller;

import dto.MatchDto;
import dto.SummaryDto;
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
import service.PlayerService;
import service.SummaryService;
import service.TeamService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MatchSummaryController {

    private MatchDto matchDto;
    private List<SummaryDto> summaries;
    private PlayerService playerService;

    @FXML private ImageView teamLogo1;
    @FXML private ImageView teamLogo2;
    @FXML private Label teamName1;
    @FXML private Label teamName2;
    @FXML private Label score;
    @FXML private Label time;
    @FXML private Label text1_team1;
    @FXML private Label text1_team2;
    @FXML private Label text2_team1;
    @FXML private Label text2_team2;
    @FXML private Label text3;

    public void initialize(MatchDto matchDto){
        this.matchDto = matchDto;
        TeamService teamService = new TeamService();
        SummaryService summaryService = new SummaryService();
        playerService = new PlayerService();

        teamName1.setText(matchDto.getTeam1().getName());
        teamName2.setText(matchDto.getTeam2().getName());
        teamLogo1.setImage(new Image("/team_logos/" + matchDto.getTeam1().getName()  + ".png"));
        teamLogo2.setImage(new Image("/team_logos/" + matchDto.getTeam2().getName()  + ".png"));

        score.setText(matchDto.getResult());

        String timeStr = matchDto.getTime().split("@")[2];
        if(timeStr.equals("Not Started")){
            timeStr = matchDto.getTime().split("@")[0] + " " + matchDto.getTime().split("@")[1];
        }
        time.setText(timeStr);

        summaries = summaryService.getSummaries(matchDto.getId_match());
        setStatsTeam(1, 1);
        setStatsTeam(2, 1);
        setStatsTeam(1, 2);
        setStatsTeam(2, 2);

        setText3();
    }

    private void setStatsTeam(int half, int team){
        int min = -1;
        int max = -1;
        String character = "";
        String team_id = "";
        int maxLength = -1;
        StringBuilder dataStr = new StringBuilder();

        switch (half){
            case 1:
                min = 0; max = 45; character = "45";
                break;
            case 2:
                min =46; max = 90; character = "90";
                break;
            default:
                break;
        }

        switch (team){
            case 1:
                team_id = matchDto.getTeam1().getId_team();
                break;
            case 2:
                team_id = matchDto.getTeam2().getId_team();
                break;
            default:
                break;
        }

        List<String> data = new ArrayList<>();

        for(SummaryDto summary: summaries){
            Integer minute = Integer.parseInt(summary.getMinute());
            String minuteStr = summary.getMinute();

            if(((min <= minute && minute <= max) || (minuteStr.contains(character))) && summary.getId_team().equals(team_id)){
                if(minute < 10){
                    switch(team){
                        case 1:
                            minuteStr += "'  ";
                            break;
                        case 2:
                            minuteStr = "  " + minuteStr + "'";
                            break;
                        default:
                            break;
                    }
                }
                else{
                    minuteStr += "'";
                }

                String type = summary.getType().toUpperCase();
                String player1 = playerService.getPlayerById(summary.getId_player1()).getName();
                String player2 = "";
                String penalty = "";
                if(!summary.getPenalty().equals("null") || !summary.getPenalty().equals("false")){
                    penalty = "(penalty)";
                }
                if(!summary.getId_player2().equals("null")){
                    player2 = " (" + playerService.getPlayerById(summary.getId_player2()).getName() + ") ";
                }
                switch (team) {
                    case 1:
                        data.add(minuteStr + " [" + type + "] " + player1 + player2 + penalty);
                        break;
                    case 2:
                        String item = penalty + player2 + player1 + " [" + type + "] " + minuteStr;
                        data.add(item);
                        if(item.length() > maxLength){
                            maxLength = item.length();
                        }
                        break;
                    default: break;
                }
            }
        }

        switch(team){
            case 1:
                dataStr = new StringBuilder();

                for(String item: data){
                    dataStr.append(item).append("\n");
                }

                switch (half){
                    case 1:
                        text1_team1.setText(dataStr.toString());
                        break;
                    case 2:
                        text2_team1.setText(dataStr.toString());
                        break;
                    default:
                        break;
                }
            case 2:
                dataStr = new StringBuilder();

                for(String item: data){
                    StringBuilder indentation = new StringBuilder();
                    for(int i=0; i<=(maxLength - item.length()); i++){
                        indentation.append(" ");
                    }
                    dataStr.append(indentation).append(item).append("\n");
                }

                switch (half){
                    case 1:
                        text1_team2.setText(dataStr.toString());
                        break;
                    case 2:
                        text2_team2.setText(dataStr.toString());
                        break;
                    default:
                        break;
                }
            default: break;
        }

    }

    private void setText3(){
        String referee = matchDto.getReferee();
        String venue = matchDto.getStadium();
        String team = teamName1.getText();
        String info = "Referee: " + referee + ", Venue: " +  venue + " (" + team + ")";

        text3.setText(info);
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
