package controller;

import dto.LineupDto;
import dto.MatchDto;
import dto.TeamDto;
import entity.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.LineupService;
import service.PlayerService;

import java.util.ArrayList;

public class AdminLienupsController {

    @FXML private Label playersLabel1;
    @FXML private Label playersLabel2;
    @FXML private Label startingLabel;
    @FXML private Label subsLabel;
    @FXML private ListView<String> players1List;
    @FXML private ListView<String> players2List;
    @FXML private ListView<String> startingList;
    @FXML private ListView<String> subsList;
    @FXML private TextField formationTextField;

    private TeamDto teamDto;
    private MatchDto matchDto;
    private int nrStarters=0;
    private int nrSubs=0;
    protected Alert alert = new Alert(Alert.AlertType.NONE);

    public void initData(TeamDto teamDto,MatchDto matchDto) {
        this.teamDto = teamDto;
        this.matchDto = matchDto;
        players1List.setItems(getPlayers());
        players2List.setItems(getPlayers());
        playersLabel1.setText("Unselected players");
        playersLabel2.setText("Unselected players");
        startingLabel.setText("Starting 11");
        subsLabel.setText("Subs");
    }

    public ObservableList<String> getPlayers(){
        ObservableList<String> players= FXCollections.observableArrayList();
        for(Player player:teamDto.getPlayers()){
            players.add(player.getName());
        }
        return players;
    }

    public void insertStarters(){
        nrStarters++;
        if(nrStarters<=11){
            String player=players1List.getSelectionModel().getSelectedItem();
            ObservableList<String> players1= players1List.getItems();
            players1.remove(player);
            players1List.setItems(players1);
            ObservableList<String> players2= players2List.getItems();
            players2.remove(player);
            players1List.setItems(players2);
            ObservableList<String> starters=startingList.getItems();
            starters.add(player);
            startingList.setItems(starters);
        }
        else{
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Already added 11 starters!");
            alert.show();
        }
    }

    public void insertSubs(){
        nrSubs++;
        if(nrSubs<=7){
            String player=players2List.getSelectionModel().getSelectedItem();
            ObservableList<String> players1= players1List.getItems();
            players1.remove(player);
            players1List.setItems(players1);
            ObservableList<String> players2= players2List.getItems();
            players2.remove(player);
            players1List.setItems(players2);
            ObservableList<String> subs=subsList.getItems();
            subs.add(player);
            subsList.setItems(subs);
        }
        else{
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText("Already added 7 subs!");
            alert.show();
        }
    }

    public void addLineup(){
        PlayerService playerService=new PlayerService();
        LineupService lineupService=new LineupService();
        LineupDto lineupDto=new LineupDto();
        lineupDto.setId_match(matchDto.getId_match());
        lineupDto.setId_team(teamDto.getId_team());
        lineupDto.setFormation(formationTextField.getText());
        ObservableList<String> starters=startingList.getItems();
        ArrayList<String> id_starters = new ArrayList<>();
        for(String starter:starters){
            id_starters.add(playerService.getPlayerByName(starter).getId_player());
        }
        lineupDto.setStarting_players_ids(id_starters);
        ObservableList<String> subs=subsList.getItems();
        ArrayList<String> id_subs=new ArrayList<>();
        for(String sub:subs){
            id_subs.add(playerService.getPlayerByName(sub).getId_player());
        }
        lineupDto.setSubstitutes_ids(id_subs);
        lineupService.insertLineup(lineupDto);
    }

}
