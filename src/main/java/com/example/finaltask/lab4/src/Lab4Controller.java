package com.example.finaltask.lab4.src;

import com.example.finaltask.ControllerApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class Lab4Controller extends ControllerApplication {
    @FXML
    private TextArea resultText;
    @FXML
    private TextField inputMessage;
    @FXML
    private final Translate tr = new Translate();
    @FXML
    protected void makeChooseFile() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        try (BufferedReader readerDataset = new BufferedReader(new FileReader(selectedFile))) {
            tr.readDataset(readerDataset);
        } catch (Exception e) {
            resultText.appendText(e.getMessage() + "\n");
        }
    }

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerApplication.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.show();
        oldStage.close();
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void startClickAction() {
        try {
            String message = inputMessage.getText();
            String translate = tr.translateAll(message);
            resultText.appendText(translate + "\n");
        } catch (Exception e) {
            resultText.appendText(e.getMessage() + "\n");
        }
    }
}