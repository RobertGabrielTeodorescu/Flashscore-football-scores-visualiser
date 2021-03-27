package start;

import controller.TeamViewFixtures;
import dto.MatchDto;
import dto.TeamDto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.MatchService;
import service.TeamService;
import service.UserService;
import utils.ApplicationUtils;

public class Main extends Application {

    public final static String APPLICATION_TITLE = "Login & Sign up";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/frames/login-frame.fxml"));
        Parent root=loader.load();
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);

    }

}
