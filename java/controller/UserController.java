package controller;

import dto.FileData;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import repository.UserRepository;

import java.net.URL;
import java.util.ResourceBundle;


public class UserController implements Initializable {

    protected Stage appStage;
    protected Parent root;

    private User user;
    private UserRepository userRepository;

    @FXML
    public Label nameLabel;
    @FXML
    public TableView<FileData> table;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table.setItems(this.getData());
    }

    public void populateWihData(User user) {
        this.user = user;
        //this.nameLabel.setText(user.getName());
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setEditable(true);
        table.setMaxSize(600, 600);
    }

    private ObservableList<FileData> getData() {
        return FXCollections.observableArrayList(
                new FileData("file1", "D:\\myFiles\\file1.txt", "25 MB", "12/01/2017"),
                new FileData("file2", "D:\\myFiles\\file2.txt", "30 MB", "01/11/2019"),
                new FileData("file3", "D:\\myFiles\\file3.txt", "50 MB", "12/04/2017"),
                new FileData("file4", "D:\\myFiles\\file4.txt", "75 MB", "25/09/2018")
        );
    }

    @FXML
    private void dodo() {
        System.err.println("Test?");
    }
}
