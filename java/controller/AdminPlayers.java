package controller;

import dto.LeagueDto;
import dto.PlayerDto;
import dto.TeamDto;
import entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import mappers.PlayerMapper;
import mappers.TeamMapper;
import service.LeagueService;
import service.PlayerService;
import service.TeamService;
import utils.ApplicationUtils;
import utils.MyStringConverter;
import validator.CustomValidators;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminPlayers implements Initializable {

    @FXML private ChoiceBox<LeagueDto> leagueSelector;
    @FXML private ChoiceBox<TeamDto> teamSelector;
    @FXML private TableView<PlayerDto> tableView;
    @FXML private TableColumn<PlayerDto,Integer> jerseyColumn;
    @FXML private TableColumn<PlayerDto,String> nameColumn;
    @FXML private TableColumn<PlayerDto,String> positionColumn;
    @FXML private TableColumn<PlayerDto,Integer> ageColumn;
    @FXML private TableColumn<PlayerDto,Integer> matchesColumn;
    @FXML private TableColumn<PlayerDto,Integer> yellowCardsColumn;
    @FXML private TableColumn<PlayerDto,Integer> redCardsColumn;
    @FXML private TableColumn<PlayerDto,Integer> goalsColumn;
    @FXML private TableColumn<PlayerDto,String> teamColumn;
    @FXML private TableColumn<PlayerDto,String> leagueColumn;
    @FXML private TableColumn<PlayerDto,String> nationalityColumn;
    @FXML private TextField nameTextField;
    @FXML private TextField teamTextField;
    @FXML private TextField nationalityTextField;
    @FXML private TextField ageTextField;
    @FXML private TextField positionTextField;
    @FXML private TextField jerseyTextField;
    @FXML private TextField matchesTextField;
    @FXML private TextField goalsTextField;
    @FXML private TextField yellowCardsTextField;
    @FXML private TextField redCardsTextField;

    private Alert alert=new Alert(Alert.AlertType.NONE);

    private PlayerService playerService;
    private TeamService teamService;
    private LeagueService leagueService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerService=new PlayerService();
        teamService=new TeamService();
        leagueService=new LeagueService();
        jerseyColumn.setCellValueFactory(new PropertyValueFactory<>("jersey_number"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("matches_played"));
        yellowCardsColumn.setCellValueFactory(new PropertyValueFactory<>("yellow_cards"));
        redCardsColumn.setCellValueFactory(new PropertyValueFactory<>("red_cards"));
        goalsColumn.setCellValueFactory(new PropertyValueFactory<>("goals_scored"));
        nationalityColumn.setCellValueFactory(new PropertyValueFactory<>("nationality"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        leagueColumn.setCellValueFactory(new PropertyValueFactory<>("leagueName"));
        tableView.setItems(getAllPlayers());
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        jerseyColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        positionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        matchesColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        yellowCardsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        redCardsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        goalsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        nationalityColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        teamColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public ObservableList<PlayerDto> getAllPlayers(){
        ObservableList<PlayerDto> playerDtos= FXCollections.observableArrayList();
        List<PlayerDto> players=playerService.getAllPlayers();
        for(PlayerDto player:players){
            TeamDto team=TeamMapper.entityToDto(player.getTeam());
            player.setLeagueName(team.getLeagues().getName());
            player.setTeamName(team.getName());
            playerDtos.add(player);
        }
        return playerDtos;
    }

    public void changeNameCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        playerDto.setName(cellEditEvent.getNewValue().toString());
        playerService.updatePlayer(playerDto);
    }

    public void changeTeamCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            TeamDto newTeam=teamService.getTeamByName(cellEditEvent.getNewValue().toString());
            playerDto.setTeam(TeamMapper.dtoToEntity(newTeam));
            playerDto.setLeagueName(newTeam.getLeagues().getName());
            playerDto.setTeamName(newTeam.getName());
            playerService.updatePlayer(playerDto);
        }catch(IndexOutOfBoundsException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("No team found! Enter a valid team name!");
            alert.show();
        }
    }

    public void changeJerseyCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            playerDto.setJersey_number((Integer) cellEditEvent.getNewValue());
            playerService.updatePlayer(playerDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for jersey");
            alert.show();
        }
    }

    public void changePositionCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        playerDto.setPosition(cellEditEvent.getNewValue().toString());
        playerService.updatePlayer(playerDto);
    }

    public void changeAgeCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            playerDto.setAge((Integer) cellEditEvent.getNewValue());
            playerService.updatePlayer(playerDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for age!");
            alert.show();
        }
    }

    public void changeMatchesCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            playerDto.setMatches_played((Integer) cellEditEvent.getNewValue());
            playerService.updatePlayer(playerDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for matches played!");
            alert.show();
        }

    }

    public void changeYellowCardsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            playerDto.setYellow_cards((Integer) cellEditEvent.getNewValue());
            playerService.updatePlayer(playerDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for yellow cards!");
            alert.show();
        }
    }

    public void changeRedCardsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            playerDto.setRed_cards((Integer) cellEditEvent.getNewValue());
            playerService.updatePlayer(playerDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for red cards!");
            alert.show();
        }
    }

    public void changeGoalsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        try{
            playerDto.setGoals_scored((Integer) cellEditEvent.getNewValue());
            playerService.updatePlayer(playerDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for goals scored!");
            alert.show();
        }
    }

    public void changeNationalityCellEdit(TableColumn.CellEditEvent cellEditEvent){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        playerDto.setNationality(cellEditEvent.getNewValue().toString());
        playerService.updatePlayer(playerDto);
    }

    public void removePlayer(){
        ObservableList<PlayerDto> players=tableView.getItems();
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        players.remove(playerDto);
        playerService.removePlayer(playerDto);
    }

    public void resetStats(){
        PlayerDto playerDto=tableView.getSelectionModel().getSelectedItem();
        playerDto.setYellow_cards(0);
        playerDto.setRed_cards(0);
        playerDto.setMatches_played(0);
        playerDto.setGoals_scored(0);
        playerService.updatePlayer(playerDto);
        tableView.setItems(getAllPlayers());
    }

    public void addPlayer(){

        PlayerDto playerDto=new PlayerDto();
        try{
            CustomValidators.validateTextField(nationalityTextField);
        playerDto.setNationality(nationalityTextField.getText());
        CustomValidators.validateTextField(goalsTextField);
        playerDto.setGoals_scored(Integer.parseInt(goalsTextField.getText()));
        CustomValidators.validateTextField(redCardsTextField);
        playerDto.setRed_cards(Integer.parseInt(redCardsTextField.getText()));
        CustomValidators.validateTextField(matchesTextField);
        playerDto.setMatches_played(Integer.parseInt(matchesTextField.getText()));
        CustomValidators.validateTextField(yellowCardsTextField);
        playerDto.setYellow_cards(Integer.parseInt(yellowCardsTextField.getText()));
        CustomValidators.validateTextField(ageTextField);
        playerDto.setAge(Integer.parseInt(ageTextField.getText()));
        CustomValidators.validateTextField(positionTextField);
        playerDto.setPosition(positionTextField.getText());
        CustomValidators.validateTextField(jerseyTextField);
        playerDto.setJersey_number(Integer.parseInt(jerseyTextField.getText()));
        CustomValidators.validateTextField(teamTextField);
        playerDto.setTeamName(teamTextField.getText());
        CustomValidators.validateTextField(nameTextField);
        playerDto.setName(nameTextField.getText());
        playerDto.setId_player(ApplicationUtils.generateUUID());

        TeamDto teamDto=teamService.getTeamByName(teamTextField.getText());
        playerDto.setLeagueName(teamDto.getLeagues().getName());
        playerDto.setTeam(TeamMapper.dtoToEntity(teamDto));
        Set<Player> players=teamDto.getPlayers();
        players.add(PlayerMapper.dtoToEntity(playerDto));
        teamDto.setPlayers(players);
        playerService.addPlayer(playerDto);
        teamService.updateTeam(teamDto);
        tableView.setItems(getAllPlayers());
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Player added");
        alert.show();

        }catch (Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Couldn't do the adding! Check again the information inserted!");
            alert.show();
        }


    }

}
