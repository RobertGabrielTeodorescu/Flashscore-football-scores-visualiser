package controller;

import dto.MatchDto;
import dto.PlayerDto;
import dto.TeamDto;
import dto.UserDto;
import entity.Match;
import entity.Player;
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
import mappers.PlayerMapper;
import service.MatchService;
import service.PlayerService;
import service.TeamService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class TeamViewSquad  {

    private TeamDto teamDto;
    private UserDto userDto;
    private PlayerService playerService;
    private TeamService teamService;
    protected Alert alert = new Alert(Alert.AlertType.NONE);

    @FXML private Label teamName;
    @FXML private ImageView imageView;
    @FXML private TableView<PlayerDto> tableView;
    @FXML private TableColumn<PlayerDto,Integer> jerseyColumn;
    @FXML private TableColumn<PlayerDto,String> nameColumn;
    @FXML private TableColumn<PlayerDto,String> positionColumn;
    @FXML private TableColumn<PlayerDto,Integer> ageColumn;
    @FXML private TableColumn<PlayerDto,Integer> matchesColumn;
    @FXML private TableColumn<PlayerDto,Integer> yellowCardsColumn;
    @FXML private TableColumn<PlayerDto,Integer> redCardsColumn;
    @FXML private TableColumn<PlayerDto,Integer> goalsColumn;

    public void initData(TeamDto teamDto,UserDto userDto){
        this.teamDto=teamDto;
        this.userDto=userDto;
        playerService=new PlayerService();
        teamService=new TeamService();
        teamName.setText(this.teamDto.getName());
        Image image=new Image("/team_logos/"+teamDto.getName()+".png");
        imageView.setImage(image);
        jerseyColumn.setCellValueFactory(new PropertyValueFactory<>("jersey_number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("matches_played"));
        yellowCardsColumn.setCellValueFactory(new PropertyValueFactory<>("yellow_cards"));
        redCardsColumn.setCellValueFactory(new PropertyValueFactory<>("red_cards"));
        goalsColumn.setCellValueFactory(new PropertyValueFactory<>("goals_scored"));
        tableView.setItems(getPlayers());
        tableView.setEditable(false);
    }

    public ObservableList<PlayerDto> getPlayers(){
        ObservableList<PlayerDto> playerDtos= FXCollections.observableArrayList();
        Set<Player> players=teamDto.getPlayers();
        for(Player player:players){
            playerDtos.add(PlayerMapper.entityToDto(player));
        }
        return playerDtos;
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
