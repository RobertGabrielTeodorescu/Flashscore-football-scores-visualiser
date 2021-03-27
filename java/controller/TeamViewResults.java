package controller;

import dto.MatchDto;
import dto.TeamDto;
import dto.UserDto;
import entity.Match;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import service.MatchService;
import service.TeamService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeamViewResults  {

    private TeamDto teamDto;
    private UserDto userDto;
    private MatchService matchService;
    private TeamService teamService;
    protected Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML private Label teamName;
    @FXML private ImageView imageView;
    @FXML private TableView<MatchDto> tableView;
    @FXML private TableColumn<MatchDto,String> dateColumn;
    @FXML private TableColumn<MatchDto,String> timeColumn;
    @FXML private TableColumn<MatchDto,String> hostsColumn;
    @FXML private TableColumn<MatchDto,String> scoreColumn;
    @FXML private TableColumn<MatchDto,String> guestsColumn;

    public void initData(TeamDto teamDto,UserDto userDto){
        this.teamDto=teamDto;
        this.userDto=userDto;
        matchService=new MatchService();
        teamService=new TeamService();
        teamName.setText(this.teamDto.getName());
        Image image=new Image("/team_logos/"+teamDto.getName()+".png");
        imageView.setImage(image);
        dateColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("hour"));
        hostsColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("hosts"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("score"));
        guestsColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("guests"));
        tableView.setItems(getMatches());
        tableView.setEditable(false);
    }

    public ObservableList<MatchDto> getMatches() {
        ObservableList<MatchDto> matchDtos = FXCollections.observableArrayList();
        List<MatchDto> results=matchService.getResults(teamDto);
        for (MatchDto matchDto : results) {
            matchDto.setDate(matchDto.getTime());
            matchDto.setHour(matchDto.getTime());
            matchDto.setHosts(matchDto.getTeam1().getName());
            matchDto.setScore(matchDto.getResult());
            matchDto.setGuests(matchDto.getTeam2().getName());
            matchDtos.add(matchDto);
        }
        return matchDtos;
    }

    public void changeToSquadFrame(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/team_view_squad.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        TeamViewSquad teamViewSquad=loader.getController();
        teamViewSquad.initData(teamDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(teamDto.getName()+"-squad");
        window.show();
    }

    public void changeToStandingsFrame(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/team_view_standings.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        TeamViewStandings teamViewStandings=loader.getController();
        teamViewStandings.initData(teamDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(teamDto.getName()+"-standings");
        window.show();
    }

    public void changeToFixturesFrame(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/team_view_fixtures.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        TeamViewFixtures teamViewFixtures=loader.getController();
        teamViewFixtures.initData(teamDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(teamDto.getName()+"-fixtures");
        window.show();
    }

    public void changeToSummary(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/match-statistics-frame.fxml"));
        Parent root=loader.load();
        Stage stage= new Stage();
        stage.setTitle("Statistics");
        stage.setScene(new Scene(root,543,708));
        MatchDto matchSelected=tableView.getSelectionModel().getSelectedItem();
        MatchStatisticsController matchStatisticsController=loader.getController();
        matchStatisticsController.initialize(matchSelected);
        stage.show();
    }

    public void addToFavorites(ActionEvent event) throws IOException{
        ArrayList<String> followers=teamDto.getId_followers();
        String userId=userDto.getId_user();
        if(!followers.contains(userId)){
            followers.add(userDto.getId_user());
            teamDto.setId_followers(followers);
            teamService.updateTeam(teamDto);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Team added to favorites!");
            alert.show();
        }
        else{
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Team already added to favorites!");
            alert.show();
        }
    }

}