package controller;

import com.sun.org.glassfish.external.statistics.Statistic;
import dto.*;
import entity.Lineup;
import entity.Match;
import entity.Team;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import mappers.MatchMapper;
import mappers.StatisticMapper;
import mappers.TeamMapper;
import service.*;
import utils.ApplicationUtils;
import utils.MyStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminMatchesController implements Initializable {

    private Alert alert=new Alert(Alert.AlertType.NONE);

    @FXML private TextField team1Text;
    @FXML private TextField team2Text;
    @FXML private TextField timeText;
    @FXML private TextField stadiumText;
    @FXML private TextField refereeText;
    @FXML private TextField resultText;

    @FXML private TableView<MatchDto> tableView;
    @FXML private TableColumn<MatchDto, String> team1Column;
    @FXML private TableColumn<MatchDto,String> team2Column;
    @FXML private TableColumn<MatchDto,String> timeColumn;
    @FXML private TableColumn<MatchDto,String> resultColumn;
    @FXML private TableColumn<MatchDto,String> stadiumColumn;
    @FXML private TableColumn<MatchDto,String> refereeColumn;

    private MatchService matchService=new MatchService();
    private TeamService teamService=new TeamService();
    private UserService userService=new UserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeColumn.setCellValueFactory(new PropertyValueFactory<MatchDto, String>("time"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("result"));
        team1Column.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("hosts"));
        team2Column.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("guests"));
        refereeColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("referee"));
        stadiumColumn.setCellValueFactory(new PropertyValueFactory<MatchDto,String>("stadium"));
        tableView.setItems(matchService.getAllMatches(true));
        tableView.setEditable(true);
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        resultColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        team1Column.setCellFactory(TextFieldTableCell.forTableColumn());
        team2Column.setCellFactory(TextFieldTableCell.forTableColumn());
        refereeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        stadiumColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void changeTeam1CellEdit(TableColumn.CellEditEvent cellEditEvent){
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        TeamDto teamDto= teamService.getTeamByName(cellEditEvent.getNewValue().toString());
        matchDto.setTeam1(TeamMapper.dtoToEntity(teamDto));
        matchDto.setHosts(teamDto.getName());
        matchService.updateMatch(matchDto);
    }

    public void changeTeam2CellEdit(TableColumn.CellEditEvent cellEditEvent){
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        TeamDto teamDto= teamService.getTeamByName(cellEditEvent.getNewValue().toString());
        matchDto.setTeam2(TeamMapper.dtoToEntity(teamDto));
        matchDto.setGuests(teamDto.getName());
        matchService.updateMatch(matchDto);
    }

    public void changeTime(TableColumn.CellEditEvent cellEditEvent){
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        matchDto.setTime(cellEditEvent.getNewValue().toString());
        matchService.updateMatch(matchDto);
    }

    public void changeResult(TableColumn.CellEditEvent cellEditEvent){
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        matchDto.setResult(cellEditEvent.getNewValue().toString());
        matchService.updateMatch(matchDto);
        Team team1=matchDto.getTeam1();
        Team team2=matchDto.getTeam2();
        for(String id: TeamMapper.entityToDto(team1).getId_followers()){
            try{
                UserDto userDto =userService.getUserById(id);
                System.out.println(userDto.getEmail());
                ApplicationUtils.sendEmail(matchDto,userDto.getEmail());
            }
            catch (Exception e){
                System.out.println("no followers found");
            }
        }
        for(String id: TeamMapper.entityToDto(team2).getId_followers()){
            try{
                UserDto userDto =userService.getUserById(id);
                ApplicationUtils.sendEmail(matchDto,userDto.getEmail());
            }
            catch (Exception e){
                System.out.println("no followers found");
            }
        }
    }

    public void changeStadium(TableColumn.CellEditEvent cellEditEvent){
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        matchDto.setStadium(cellEditEvent.getNewValue().toString());
        matchService.updateMatch(matchDto);
    }

    public void changeReferee(TableColumn.CellEditEvent cellEditEvent){
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        matchDto.setReferee(cellEditEvent.getNewValue().toString());
        matchService.updateMatch(matchDto);
    }

    public void removeMatch(){
        ObservableList<MatchDto> matches=tableView.getItems();
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        matches.remove(matchDto);
        matchService.deleteMatch(matchDto);
    }

    private StatisticDto createNewStatistics(){
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setBall_possession("50-50");
        statisticDto.setGoal_attempts("0-0");
        statisticDto.setShots_on_goal("0-0");
        statisticDto.setShots_off_goal("0-0");
        statisticDto.setBlocked_shots("0-0");
        statisticDto.setFree_kicks("0-0");
        statisticDto.setCorner_kicks("0-0");
        statisticDto.setOffsides("0-0");
        statisticDto.setFoults("0-0");
        statisticDto.setYellow_cards("0-0");
        statisticDto.setRed_cards("0-0");
        statisticDto.setTotal_passes("0-0");

        return statisticDto;
    }


    public void addMatch(){
        MatchDto matchDto=new MatchDto();

        try{
            matchDto.setTime(timeText.getText());
            matchDto.setStadium(stadiumText.getText());
            matchDto.setReferee(refereeText.getText());
            matchDto.setResult(resultText.getText());
            matchDto.setId_match(ApplicationUtils.generateUUID());

            StatisticDto statisticDto = createNewStatistics();
            statisticDto.setId_statistics(ApplicationUtils.generateUUID());
            StatisticService statisticService = new StatisticService();
            statisticService.addStatistic(statisticDto);

            matchDto.setStatistics(StatisticMapper.dtoToEntityS(statisticDto));

            TeamDto teamDto1=teamService.getTeamByName(team1Text.getText());
            TeamDto teamDto2=teamService.getTeamByName(team2Text.getText());

            matchDto.setTeam1(TeamMapper.dtoToEntity(teamDto1));
            matchDto.setTeam2(TeamMapper.dtoToEntity(teamDto2));

            Set<Match> hostMatches=teamDto1.getHostMatches();
            hostMatches.add(MatchMapper.dtoToEntity(matchDto));
            teamDto1.setHostMatches(hostMatches);

            Set<Match> guestMatches=teamDto2.getGuestMatches();
            guestMatches.add(MatchMapper.dtoToEntity(matchDto));
            teamDto2.setGuestMatches(guestMatches);

            statisticDto.setMatch(MatchMapper.dtoToEntity(matchDto));

            matchService.insertMatch(matchDto);
            teamService.updateTeam(teamDto1);
            teamService.updateTeam(teamDto2);

            tableView.setItems(matchService.getAllMatches(true));

            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Match added");
            alert.show();

        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Match not added! Check the information inserted!");
            alert.show();
        }
    }

    public void updateStatistics(ActionEvent actionEvent) throws IOException {
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_statistics.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Add New Statistics");

        AdminStatisticsController adminStatisticsController = loader.getController();
        adminStatisticsController.initialize(matchDto);

        stage.setScene(new Scene(viewParent,600,800));
        stage.show();
    }

    public void updateSummary(ActionEvent actionEvent) throws IOException{
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_summary.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Add New Summary");

        AdminSummaryController adminSummaryController = loader.getController();
        adminSummaryController.initialize(matchDto);

        stage.setScene(new Scene(viewParent,400,400));
        stage.show();
    }

    public void changeToLineupsFrame(ActionEvent actionEvent) throws IOException{
        MatchDto matchDto=tableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_lineups.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Add new lineup for "+matchDto.getTeam1().getName());

        AdminLienupsController adminLienupsController = loader.getController();
        adminLienupsController.initData(TeamMapper.entityToDto(matchDto.getTeam1()),matchDto);

        stage.setScene(new Scene(viewParent,600,525));
        stage.show();

        FXMLLoader loader1=new FXMLLoader();
        loader1.setLocation(getClass().getResource("/frames/admin_lineups.fxml"));
        Parent viewParent1=loader1.load();
        Stage stage1=new Stage();
        stage1.setTitle("Add new lineup for "+matchDto.getTeam2().getName());

        AdminLienupsController adminLienupsController1 = loader1.getController();
        adminLienupsController1.initData(TeamMapper.entityToDto(matchDto.getTeam2()),matchDto);

        stage1.setScene(new Scene(viewParent1,600,525));
        stage1.show();

    }
}
