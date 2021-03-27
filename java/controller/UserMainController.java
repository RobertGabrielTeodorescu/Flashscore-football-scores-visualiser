package controller;

import dto.LeagueDto;
import dto.MatchDto;
import dto.TeamDto;
import dto.UserDto;
import entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.LeagueService;
import service.MatchService;
import service.TeamService;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;


public class UserMainController  {

    private UserDto userDto;
    private TeamService teamService;
    private LeagueService leagueService;
    private MatchService matchService;

    @FXML private ListView myTeamsListView;
    @FXML private ListView myLeaguesListView;

    @FXML private TableView<MatchDto> allMatches;
    @FXML private TableColumn<MatchDto, SimpleStringProperty> timeColumn;
    @FXML private TableColumn<MatchDto,String> team1Column;
    @FXML private TableColumn<MatchDto,String> team2Column;
    @FXML private TableColumn<MatchDto,String> scoreColumn;
    @FXML private TableColumn<MatchDto,String> refereeColumn;
    @FXML private TableColumn<MatchDto,String> stadiumColumn;


    @FXML private TableView<MatchDto> liveMatches;
    @FXML private TableColumn<MatchDto, SimpleStringProperty> minuteLiveColumn;
    @FXML private TableColumn<MatchDto,String> team1LiveColumn;
    @FXML private TableColumn<MatchDto,String> team2LiveColumn;
    @FXML private TableColumn<MatchDto,String> scoreLiveColumn;
    @FXML private TableColumn<MatchDto,String> refereeLiveColumn;
    @FXML private TableColumn<MatchDto,String> stadiumLiveColumn;


    @FXML private TableView<MatchDto> finishedMatches;
    @FXML private TableColumn<MatchDto, SimpleStringProperty> statusFinishedColumn;
    @FXML private TableColumn<MatchDto,String> team1FinishedColumn;
    @FXML private TableColumn<MatchDto,String> team2FinishedColumn;
    @FXML private TableColumn<MatchDto,String> scoreFinishedColumn;
    @FXML private TableColumn<MatchDto,String> refereeFinishedColumn;
    @FXML private TableColumn<MatchDto,String> stadiumFinishedColumn;

    public void initData(UserDto userDto){

        this.userDto=userDto;
        teamService=new TeamService();
        leagueService=new LeagueService();
        matchService=new MatchService();
        LeagueService leagueService=new LeagueService();

        for(TeamDto team:teamService.getMyTeams(this.userDto)){
            myTeamsListView.getItems().add(team.getName());
        }
        for(LeagueDto league:getAllLeagues()){
            myLeaguesListView.getItems().add(league.getName());
        }
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        team1Column.setCellValueFactory(new PropertyValueFactory<>("hosts"));
        team2Column.setCellValueFactory(new PropertyValueFactory<>("guests"));
        refereeColumn.setCellValueFactory(new PropertyValueFactory<>("referee"));
        stadiumColumn.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        allMatches.setItems(getAllMatches());

        minuteLiveColumn.setCellValueFactory(new PropertyValueFactory<>("minute"));
        scoreLiveColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        team1LiveColumn.setCellValueFactory(new PropertyValueFactory<>("hosts"));
        team2LiveColumn.setCellValueFactory(new PropertyValueFactory<>("guests"));
        refereeLiveColumn.setCellValueFactory(new PropertyValueFactory<>("referee"));
        stadiumLiveColumn.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        liveMatches.setItems(getLiveMatches());


        statusFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("minute"));
        scoreFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        team1FinishedColumn.setCellValueFactory(new PropertyValueFactory<>("hosts"));
        team2FinishedColumn.setCellValueFactory(new PropertyValueFactory<>("guests"));
        refereeFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("referee"));
        stadiumFinishedColumn.setCellValueFactory(new PropertyValueFactory<>("stadium"));
        finishedMatches.setItems(getFinishedMatches());
    }


    public void viewTeamPressed(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/team_view_fixtures.fxml"));
        Parent root=loader.load();
        Stage stage= new Stage();
        stage.setScene(new Scene(root,800,600));
        TeamDto teamSelected=teamService.getTeamByName(myTeamsListView.getSelectionModel().getSelectedItem().toString());
        TeamViewFixtures teamViewFixtures=loader.getController();
        teamViewFixtures.initData(teamSelected,userDto);
        stage.show();

    }

    public void viewLeaguePressed(ActionEvent e) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/league_view_standings.fxml"));
        Parent root=loader.load();
        Stage stage= new Stage();
        stage.setScene(new Scene(root,800,600));
        LeagueDto leagueDto=leagueService.getLeagueByName(myLeaguesListView.getSelectionModel().getSelectedItem().toString());
        LeagueViewStandings leagueViewStandings=loader.getController();
        leagueViewStandings.initDataL(leagueDto,userDto);
        stage.setTitle(leagueDto.getName());
        stage.show();
    }

    public void viewMatchPressed(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/match-statistics-frame.fxml"));
        Parent root=loader.load();
        Stage stage= new Stage();
        stage.setTitle("Statistics");
        stage.setScene(new Scene(root,543,708));
        MatchDto matchSelected=new MatchDto();
        if(allMatches.getSelectionModel().getSelectedItem()!=null){
            matchSelected=allMatches.getSelectionModel().getSelectedItem();
        }
        else{
            if(liveMatches.getSelectionModel().getSelectedItem()!=null){
                matchSelected=liveMatches.getSelectionModel().getSelectedItem();
            }
            else{
                if(finishedMatches.getSelectionModel().getSelectedItem()!=null){
                    matchSelected=finishedMatches.getSelectionModel().getSelectedItem();
                }
            }
        }
        MatchStatisticsController matchStatisticsController=loader.getController();
        matchStatisticsController.initialize(matchSelected);
        stage.show();
    }

    public void refreshPage(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/user-home.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        UserMainController userMainController=loader.getController();
        userMainController.initData(userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle("Main page");
        window.show();
    }

    public ObservableList<LeagueDto> getAllLeagues() {
        ObservableList<LeagueDto> leagues = FXCollections.observableArrayList();
        LeagueService leagueService=new LeagueService();
        for(LeagueDto league: leagueService.getLeagues()){
            leagues.add(league);
        }
        return leagues;
    }

    public ObservableList<MatchDto> getLiveMatches() {
        ObservableList<MatchDto> matches = FXCollections.observableArrayList();
        List<MatchDto> matchDtos=matchService.getMatches();
        for(MatchDto match:matchDtos){
            match.setMinute(match.getTime());
            match.setHosts(match.getTeam1().getName());
            match.setGuests(match.getTeam2().getName());
            if(!match.getMinute().equals("Finished")&&!match.getMinute().equals("Not Started")){
                matches.add(match);
            }
        }
        return matches;
    }

    public ObservableList<MatchDto> getAllMatches() {
        ObservableList<MatchDto> matches = FXCollections.observableArrayList();
        List<MatchDto> matchDtos=matchService.getMatches();
        for(MatchDto match:matchDtos){
            match.setMinute(match.getTime());
            match.setDate(match.getTime());
            match.setHosts(match.getTeam1().getName());
            match.setGuests(match.getTeam2().getName());
            if(match.getMinute().equals("Not Started")){
                matches.add(match);
            }
        }
        return matches;
    }

    public ObservableList<MatchDto> getFinishedMatches() {
        ObservableList<MatchDto> matches = FXCollections.observableArrayList();
        List<MatchDto> matchDtos=matchService.getMatches();
        for(MatchDto match:matchDtos){
            match.setMinute(match.getTime());
            match.setHosts(match.getTeam1().getName());
            match.setGuests(match.getTeam2().getName());
            if(match.getMinute().equals("Finished")){
                matches.add(match);
            }
        }
        return matches;
    }

}
