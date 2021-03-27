package controller;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class AdminHomeController {


    public void showLeaguesData(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_leagues.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Leagues");
        stage.setScene(new Scene(viewParent,1000,600));
        stage.show();
    }

    public void showTeamsData(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_teams.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Teams");
        stage.setScene(new Scene(viewParent,1000,600));
        stage.show();
    }

    public void showPlayersData(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_players.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Players");
        stage.setScene(new Scene(viewParent,1000,600));
        stage.show();
    }

    public void showMatchesData(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/admin_matches.fxml"));
        Parent viewParent=loader.load();
        Stage stage=new Stage();
        stage.setTitle("Matches");
        stage.setScene(new Scene(viewParent,1000,600));
        stage.show();
    }

}
