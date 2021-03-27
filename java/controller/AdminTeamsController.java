package controller;

import dto.LeagueDto;
import dto.PlayerDto;
import dto.TeamDto;
import entity.Leagues;
import entity.Player;
import entity.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import mappers.LeagueMapper;
import mappers.TeamMapper;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import service.LeagueService;
import service.PlayerService;
import service.TeamService;
import utils.ApplicationUtils;
import utils.MyStringConverter;
import validator.CustomValidators;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminTeamsController implements Initializable {

    private Alert alert=new Alert(Alert.AlertType.NONE);

    @FXML private TableView<TeamDto> tableView;
    @FXML private TableColumn<TeamDto,String> nameColumn;
    @FXML private TableColumn<TeamDto,String> leagueColumn;
    @FXML private TableColumn<TeamDto,Integer> rankColumn;
    @FXML private TableColumn<TeamDto,Integer> matchesColumn;
    @FXML private TableColumn<TeamDto,Integer> winsColumn;
    @FXML private TableColumn<TeamDto,Integer> drawsColumn;
    @FXML private TableColumn<TeamDto,Integer> lossesColumn;
    @FXML private TableColumn<TeamDto,String> goalsColumn;
    @FXML private TableColumn<TeamDto,Integer> pointsColumn;
    @FXML private TableColumn<TeamDto,String> coachColumn;
    @FXML private TextField nameTextField;
    @FXML private TextField leagueTextField;
    @FXML private TextField rankTextField;
    @FXML private TextField matcesTextField;
    @FXML private TextField winsTextField;
    @FXML private TextField drawsTextField;
    @FXML private TextField lossesTextField;
    @FXML private TextField goalsTextField;
    @FXML private TextField pointsTextField;
    @FXML private TextField coachTextField;

    private TeamService teamService;
    private LeagueService leagueService;
    private PlayerService playerService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teamService=new TeamService();
        leagueService=new LeagueService();
        playerService=new PlayerService();
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        leagueColumn.setCellValueFactory(new PropertyValueFactory<>("league_name"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("matches_played"));
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));
        drawsColumn.setCellValueFactory(new PropertyValueFactory<>("draws"));
        lossesColumn.setCellValueFactory(new PropertyValueFactory<>("losses"));
        goalsColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        coachColumn.setCellValueFactory(new PropertyValueFactory<>("coach"));
        tableView.setItems(getAllTeams());
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        rankColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        leagueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        matchesColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        winsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        drawsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        lossesColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        pointsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        coachColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pointsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
    }

    public ObservableList<TeamDto> getAllTeams(){
        ObservableList<TeamDto> teamDtos= FXCollections.observableArrayList();
        List<TeamDto> teams=teamService.getAllTeams();
        for(TeamDto team:teams){
            LeagueDto league=LeagueMapper.entityToDtoL(team.getLeagues());
            team.setLeague_name(league.getName());
            teamDtos.add(team);
        }
        return teamDtos;
    }

    public void changeNameCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        teamDto.setName(cellEditEvent.getNewValue().toString());
        teamService.updateTeam(teamDto);
    }

    public void changeLeagueCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        LeagueDto leagueDto= leagueService.getLeagueByName(cellEditEvent.getNewValue().toString());
        teamDto.setLeagues(LeagueMapper.dtoToEntityL(leagueDto));
        teamService.updateTeam(teamDto);
    }

    public void changeRankCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        try{
            teamDto.setRank((Integer) cellEditEvent.getNewValue());
            teamService.updateTeam(teamDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for rank");
            alert.show();
        }
    }

    public void changeMatchesCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        try{
            teamDto.setMatches_played((Integer) cellEditEvent.getNewValue());
            teamService.updateTeam(teamDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for rank");
            alert.show();
        }
    }

    public void changeWinsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        try{
            teamDto.setWins((Integer) cellEditEvent.getNewValue());
            teamService.updateTeam(teamDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for rank");
            alert.show();
        }
    }

    public void changeDrawsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        try{
            teamDto.setDraws((Integer) cellEditEvent.getNewValue());
            teamService.updateTeam(teamDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for rank");
            alert.show();
        }
    }

    public void changeLossesCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        try{
            teamDto.setLosses((Integer) cellEditEvent.getNewValue());
            teamService.updateTeam(teamDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for rank");
            alert.show();
        }
    }

    public void changeGoalsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        try{
            teamDto.setGoals(cellEditEvent.getNewValue().toString());
            teamService.updateTeam(teamDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for rank");
            alert.show();
        }
    }

    public void changePointsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        teamDto.setPoints((Integer) cellEditEvent.getNewValue());
        teamService.updateTeam(teamDto);
    }

    public void changeCoachCellEdit(TableColumn.CellEditEvent cellEditEvent){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        teamDto.setCoach(cellEditEvent.getNewValue().toString());
        teamService.updateTeam(teamDto);
    }

    public void removeTeam(){
        ObservableList<TeamDto> teams=tableView.getItems();
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        teams.remove(teamDto);
        teamService.removeTeam(teamDto);
    }

    public void resetStats(){
        TeamDto teamDto=tableView.getSelectionModel().getSelectedItem();
        teamDto.setMatches_played(0);
        teamDto.setWins(0);
        teamDto.setDraws(0);
        teamDto.setLosses(0);
        teamDto.setGoals("0-0");
        teamService.updateTeam(teamDto);
        tableView.setItems(getAllTeams());
    }

    public void addTeam(){

        TeamDto teamDto=new TeamDto();
        try{
            CustomValidators.validateTextField(coachTextField);
        teamDto.setCoach(coachTextField.getText());
        CustomValidators.validateTextField(pointsTextField);
        teamDto.setPoints(Integer.parseInt(pointsTextField.getText()));
        CustomValidators.validateTextField(goalsTextField);
        teamDto.setGoals(goalsTextField.getText());
        CustomValidators.validateTextField(lossesTextField);
        teamDto.setLosses(Integer.parseInt(lossesTextField.getText()));
        CustomValidators.validateTextField(drawsTextField);
        teamDto.setDraws(Integer.parseInt(drawsTextField.getText()));
        CustomValidators.validateTextField(winsTextField);
        teamDto.setWins(Integer.parseInt(winsTextField.getText()));
        CustomValidators.validateTextField(matcesTextField);
        teamDto.setMatches_played(Integer.parseInt(matcesTextField.getText()));
        CustomValidators.validateTextField(coachTextField);
        teamDto.setCoach(coachTextField.getText());
        CustomValidators.validateTextField(rankTextField);
        teamDto.setRank(Integer.parseInt(rankTextField.getText()));
        CustomValidators.validateTextField(nameTextField);
        teamDto.setName(nameTextField.getText());
        CustomValidators.validateTextField(leagueTextField);
        teamDto.setLeague_name(leagueTextField.getText());
        teamDto.setId_followers(new ArrayList<>());
        teamDto.setId_team(ApplicationUtils.generateUUID());

        LeagueDto leagueDto=leagueService.getLeagueByName(leagueTextField.getText());
        teamDto.setLeagues(LeagueMapper.dtoToEntityL(leagueDto));
        Set<Team> teams=leagueDto.getTeams();
        teams.add(TeamMapper.dtoToEntity(teamDto));
        leagueDto.setTeams(teams);
        teamService.addTeam(teamDto);
        leagueService.updateLeague(leagueDto);
        tableView.setItems(getAllTeams());

        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Team added");
        alert.show();
        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Couldn't do the adding! Check again the information inserted!");
            alert.show();
        }

    }

}
