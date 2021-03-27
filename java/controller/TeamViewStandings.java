package controller;

import dto.MatchDto;
import dto.TeamDto;
import dto.UserDto;
import entity.Match;
import entity.Team;
import entity.User;
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
import mappers.TeamMapper;
import service.MatchService;
import service.TeamService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class TeamViewStandings  {

    private TeamDto teamDto;
    private UserDto userDto;
    private TeamService teamService;
    protected Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML private Label teamName;
    @FXML private ImageView logo;
    @FXML private TableView<TeamDto> tableView;
    @FXML private TableColumn<TeamDto,Integer> rankColumn;
    @FXML private TableColumn<TeamDto,String> teamColumn;
    @FXML private TableColumn<TeamDto,Integer> matchesColumn;
    @FXML private TableColumn<TeamDto,Integer> winsColumn;
    @FXML private TableColumn<TeamDto,Integer> drawsColumn;
    @FXML private TableColumn<TeamDto,Integer> lossesColumn;
    @FXML private TableColumn<TeamDto,String> goalsColumn;
    @FXML private TableColumn<TeamDto,Integer> pointsColumn;

    public void initData(TeamDto teamDto,UserDto userDto){
        this.teamDto=teamDto;
        this.userDto=userDto;
        teamService=new TeamService();
        teamName.setText(this.teamDto.getName());
        Image image=new Image("/team_logos/"+teamDto.getName()+".png");
        logo.setImage(image);
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("matches_played"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        drawsColumn.setCellValueFactory(new PropertyValueFactory<>("draws"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<>("losses"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        goalsColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
        tableView.setItems(getStandings());
        tableView.setEditable(false);
    }

    public ObservableList<TeamDto> getStandings(){
        ObservableList<TeamDto> teamDtos= FXCollections.observableArrayList();
        Set<Team> teams=teamDto.getLeagues().getTeams();
        for(Team team:teams){
            teamDtos.add(TeamMapper.entityToDto(team));
        }
        return teamDtos;
    }

    public void changeToResultsFrame(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/team_view_results.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        TeamViewResults teamViewResults=loader.getController();
        teamViewResults.initData(teamDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(teamDto.getName()+"-results");
        window.show();
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
