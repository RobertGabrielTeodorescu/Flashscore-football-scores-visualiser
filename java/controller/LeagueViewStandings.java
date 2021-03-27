package controller;

import dto.LeagueDto;
import dto.MatchDto;
import dto.TeamDto;
import dto.UserDto;
import entity.Match;
import entity.Team;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import mappers.TeamMapper;
import service.LeagueService;
import service.MatchService;
import service.TeamService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class LeagueViewStandings  {

    private LeagueDto leagueDto;
    private UserDto userDto;

    @FXML private Label teamName;
    //@FXML private Label leagueName;
    @FXML private Label stadium;
    @FXML private ImageView imageView;
    @FXML private TableView<TeamDto> tableView;
    @FXML private TableColumn<TeamDto,Integer> rankColumn;
    @FXML private TableColumn<TeamDto,String> teamColumn;
    @FXML private TableColumn<TeamDto,Integer> matchesColumn;
    @FXML private TableColumn<TeamDto,Integer> winsColumn;
    @FXML private TableColumn<TeamDto,Integer> drawsColumn;
    @FXML private TableColumn<TeamDto,Integer> lossesColumn;
    @FXML private TableColumn<TeamDto,String> goalsColumn;
    @FXML private TableColumn<TeamDto,Integer> pointsColumn;

    public void initDataL(LeagueDto leagueDto,UserDto userDto){

        this.leagueDto = leagueDto;
        this.userDto=userDto;
        teamName.setText(this.leagueDto.getName());
        Image image=new Image("/league_logos/"+leagueDto.getName()+".png");
        imageView.setImage(image);
        stadium.setText(" ");
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
        Set<Team> teams=leagueDto.getTeams();
        for(Team team:teams){
            teamDtos.add(TeamMapper.entityToDto(team));
        }
        return teamDtos;
    }

    public void changeToResultsFrameL(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/league_view_results.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        LeagueViewResults leagueViewResults=loader.getController();
        leagueViewResults.initData(leagueDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(leagueDto.getName()+"-results");
        window.show();
    }


    public void changeToFixturesFrameL(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/league_view_fixtures.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        LeagueViewFixtures leagueViewFixtures=loader.getController();
        leagueViewFixtures.initData(leagueDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(leagueDto.getName()+"-fixtures");
        window.show();
    }

    public void changeToTeamView(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/team_view_fixtures.fxml"));
        Parent root=loader.load();
        Stage stage= new Stage();
        stage.setScene(new Scene(root,800,600));
        TeamDto teamSelected=tableView.getSelectionModel().getSelectedItem();
        TeamViewFixtures teamViewFixtures=loader.getController();
        teamViewFixtures.initData(teamSelected,userDto);
        stage.show();
    }

}