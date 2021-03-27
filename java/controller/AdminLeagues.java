package controller;

import dto.LeagueDto;
import dto.PlayerDto;
import dto.TeamDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import service.LeagueService;
import utils.MyStringConverter;
import validator.CustomValidators;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminLeagues implements Initializable {

    @FXML private TableView<LeagueDto> tableView;
    @FXML private TableColumn<LeagueDto,String> nameColumn;
    @FXML private TableColumn<LeagueDto, Integer> numberOfTeamsColumn;
    @FXML private TableColumn<LeagueDto, Integer> currentFixtureColumn;
    @FXML private TextField nameTextField;
    @FXML private TextField numberOfTeamsTextField;
    @FXML private TextField currentFixtureTextField;

    private Alert alert=new Alert(Alert.AlertType.NONE);

    private LeagueService leagueService;

    public void initialize(URL location, ResourceBundle resources){
        leagueService=new LeagueService();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberOfTeamsColumn.setCellValueFactory(new PropertyValueFactory<>("number_of_teams"));
        currentFixtureColumn.setCellValueFactory(new PropertyValueFactory<>("current_fixture"));
        tableView.setItems(getAllLeagues());
        tableView.setEditable(true);
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        numberOfTeamsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
        currentFixtureColumn.setCellFactory(TextFieldTableCell.forTableColumn(new MyStringConverter()));
    }

    public ObservableList<LeagueDto> getAllLeagues(){
        ObservableList<LeagueDto> leagueDtos= FXCollections.observableArrayList();
        List<LeagueDto> leagues=leagueService.getLeagues();
        for(LeagueDto league:leagues){
            league.setName(league.getName());
            league.setNumber_of_teams(league.getNumber_of_teams());
            league.setCurrent_fixture(league.getCurrent_fixture());

            leagueDtos.add(league);
        }
        return leagueDtos;
    }

    public void changeLeagueNameCellEdit(TableColumn.CellEditEvent cellEditEvent){
        LeagueDto leagueDto=tableView.getSelectionModel().getSelectedItem();
        leagueDto.setName(cellEditEvent.getNewValue().toString());
        leagueService.updateLeague(leagueDto);
    }

    public void changeNumberOfTeamsCellEdit(TableColumn.CellEditEvent cellEditEvent){
        LeagueDto leagueDto=tableView.getSelectionModel().getSelectedItem();
        try{
            leagueDto.setNumber_of_teams((Integer) cellEditEvent.getNewValue());
            leagueService.updateLeague(leagueDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for age!");
            alert.show();
        }
    }

    public void changeCurrentFixtureCellEdit(TableColumn.CellEditEvent cellEditEvent){
        LeagueDto leagueDto=tableView.getSelectionModel().getSelectedItem();
        try{
            leagueDto.setCurrent_fixture((Integer) cellEditEvent.getNewValue());
            leagueService.updateLeague(leagueDto);
        }catch (NumberFormatException e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Enter a valid number for age!");
            alert.show();
        }
    }

    public void removeLeague(){
        ObservableList<LeagueDto> leagues=tableView.getItems();
        LeagueDto leagueDto=tableView.getSelectionModel().getSelectedItem();
        leagues.remove(leagueDto);
        leagueService.removeLeague(leagueDto);
    }

    public void addLeague(){
        LeagueDto leagueDto = new LeagueDto();
        try{
            CustomValidators.validateTextField(nameTextField);
            leagueDto.setName(nameTextField.getText());
            CustomValidators.validateTextField(numberOfTeamsTextField);
            leagueDto.setNumber_of_teams(Integer.parseInt(numberOfTeamsTextField.getText()));
            CustomValidators.validateTextField(currentFixtureTextField);
            leagueDto.setCurrent_fixture(Integer.parseInt(currentFixtureTextField.getText()));
            leagueService.addLeague(leagueDto);
            tableView.setItems(getAllLeagues());
        }catch(Exception e){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Couldn't do the adding! Check again the information inserted!");
            alert.show();
        }
    }
}
