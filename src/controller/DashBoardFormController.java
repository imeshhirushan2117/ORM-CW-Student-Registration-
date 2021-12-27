package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {

    public AnchorPane paneDashBoard;

    public void registrationOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RegistrationForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneDashBoard.getChildren().clear();
        paneDashBoard.getChildren().add(load);

    }

    public void programsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ProgramsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneDashBoard.getChildren().clear();
        paneDashBoard.getChildren().add(load);
    }

    public void studentManageOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/StudentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneDashBoard.getChildren().clear();
        paneDashBoard.getChildren().add(load);
    }

    public void exitOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/LogInForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) paneDashBoard.getScene().getWindow();
        window.setScene(new Scene(load));
        window.centerOnScreen();
    }
}
