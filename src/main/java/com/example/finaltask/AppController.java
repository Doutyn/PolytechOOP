package com.example.finaltask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppController {

    private void openNewStage(String fxml, ActionEvent event) throws IOException {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerApplication.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Application");
        newStage.show();
        oldStage.close();
    }
    @FXML
    protected void onLab1ButtonClick(ActionEvent event) throws IOException {
        openNewStage("lab1.fxml", event);
    }
    @FXML
    protected void onLab2ButtonClick(ActionEvent event) throws IOException {
        openNewStage("lab2.fxml", event);
    }
    @FXML
    protected void onLab3ButtonClick(ActionEvent event) throws IOException {
        openNewStage("lab3.fxml", event);
    }
    @FXML
    protected void onLab4ButtonClick(ActionEvent event) throws IOException {
        openNewStage("lab4.fxml", event);
    }
    @FXML
    protected void onLab5ButtonClick(ActionEvent event) throws IOException {
        openNewStage("lab5.fxml", event);
    }
    @FXML
    protected void onLab6ButtonClick(ActionEvent event) throws IOException {
        openNewStage("lab6.fxml", event);
    }
    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }
}