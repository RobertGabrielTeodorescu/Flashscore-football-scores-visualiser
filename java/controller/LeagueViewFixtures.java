package controller;

import dto.LeagueDto;
import dto.MatchDto;
import dto.TeamDto;
import dto.UserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import service.LeagueService;
import service.MatchService;
import service.TeamService;

import java.io.IOException;
import java.util.List;

public class LeagueViewFixtures {

    private TeamDto teamDto;
    private MatchService matchService;
    private LeagueDto leagueDto;
    private MatchDto matchDto;
    private TeamService teamService;
    private UserDto userDto;

    @FXML
    private Label teamName;
    @FXML private Label stadium;
    @FXML private ImageView imageView;
    @FXML private TableView<MatchDto> tableView;
    @FXML private TableColumn<MatchDto,String> dateColumn;
    @FXML private TableColumn<MatchDto,String> timeColumn;
    @FXML private TableColumn<MatchDto,String> hostsColumn;
    @FXML private TableColumn<MatchDto,String> scoreColumn;
    @FXML private TableColumn<MatchDto,String> guestsColumn;
    @FXML private TableColumn<MatchDto,String> stadiumColumn;
    @FXML private TableColumn<MatchDto,String> refereeColumn;
    @FXML private TableColumn<MatchDto,String> statisticsColumn;

    public void initData(LeagueDto leagueDto,UserDto userDto){
        //matchService=new MatchService();
        this.matchDto = matchDto;
        this.leagueDto=leagueDto;
        this.userDto=userDto;
        this.matchService=new MatchService();
        teamService=new TeamService();
        teamName.setText(leagueDto.getName());
        Image image=new Image("/league_logos/"+leagueDto.getName()+".png");
        imageView.setImage(image);
        stadium.setText(" ");
        dateColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("hour"));
        hostsColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("hosts"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("score"));
        guestsColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("guests"));
        stadiumColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("stadium"));
        refereeColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("referee"));
        tableView.setItems(getFixtures());
        tableView.setEditable(false);
    }

    public ObservableList<MatchDto> getFixtures(){
        ObservableList<MatchDto> matchDtos = FXCollections.observableArrayList();
        System.out.println(leagueDto.getId_league());
        List<MatchDto> results=matchService.getFixturesByLeague(leagueDto.getId_league());
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


    public void changeToStandingsFrameL(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/league_view_standings.fxml"));
        Parent viewParent=loader.load();
        Scene viewScene=new Scene(viewParent);
        LeagueViewStandings leagueViewStandings=loader.getController();
        leagueViewStandings.initDataL(leagueDto,userDto);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewScene);
        window.setTitle(leagueDto.getName()+"-standings");
        window.show();
    }
}
