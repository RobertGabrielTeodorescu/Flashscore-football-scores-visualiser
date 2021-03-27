package controller;

import dto.UserDto;
import entity.User;

import exception.EntityNotExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.UserRepository;
import service.UserService;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController  {

    protected Alert alert = new Alert(Alert.AlertType.NONE);
    protected UserService userService=new UserService();
    private UserDto userDto;

    @FXML private TextField usernameTextFieldLogin;
    @FXML private TextField passwordTextFieldLogin;
    @FXML private TextField usernameTextFieldSignUp;
    @FXML private TextField passwordTextFiedlSignUp;
    @FXML private TextField emailTextField;



    @FXML
    public void doLogin(ActionEvent event) throws Exception {
        String username=usernameTextFieldLogin.getText();
        String password=passwordTextFieldLogin.getText();
        if (username.equals("admin") && password.equals("admin")) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/admin-home-frame.fxml"));
            Parent root = loader.load();
            Stage stage=new Stage();
            Scene scene = new Scene(root);

            stage.setWidth(430);
            stage.setHeight(310);
            stage.setTitle("Admin page");
            stage.setScene(scene);

            stage.show();

        } else {
            try{
                /*
                userDto=userService.getUserByName(username);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/user-home.fxml"));
                Parent root = loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,850,650));
                stage.setTitle("Main page");
                UserMainController userMainScreenController=loader.getController();
                userMainScreenController.initData(userDto);
                stage.show();*/

            }catch (Exception e) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Invalid login.");
                alert.show();
            }

                userDto=userService.getUserByName(username);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/frames/user-home.fxml"));
                Parent root = loader.load();
                Stage stage=new Stage();
                stage.setScene(new Scene(root,850,650));
                stage.setTitle("Main page");
                UserMainController userMainScreenController=loader.getController();
                userMainScreenController.initData(userDto);
                stage.show();

        }
    }

    public void doSignUp(){
        String username=usernameTextFieldSignUp.getText();
        String password=passwordTextFiedlSignUp.getText();
        String email=emailTextField.getText();
        try{
            UserDto userDto=userService.getUserByName(username);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("User already exists!");
            alert.show();
        }catch (IndexOutOfBoundsException e){
            UserDto userDto=new UserDto();
            userDto.setUsername(username);
            if(password.length()<=6){
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Password too short!");
                alert.show();
            }
            else{
                userDto.setPassword(password);
                if(!email.endsWith("@gmail.com")){
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Email not valid!");
                    alert.show();
                }
                else{
                    userDto.setEmail(email);
                    userDto.setType("user");
                    userService.addUser(userDto);
                    alert.setAlertType(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("You successfully signed up!");
                    alert.show();
                }
            }
        }
    }

}
