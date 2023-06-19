package com.example.finaltask.lab1.src;

import com.example.finaltask.ControllerApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab1Controller extends ControllerApplication {
    @FXML
    private TextField startPos;

    @FXML
    private TextField startModify;

    @FXML
    private TextField strategyChoice;

    @FXML
    private TextField resultText;

    private final Hero billy = new Hero();

    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Stage oldStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(ControllerApplication.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Application");
        newStage.show();
        oldStage.close();
    }
    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    private void executionStrategy(double basePose, double modify) {
        String str = strategyChoice.getText();
        switch (str) {
            case "walk":
                billy.setMove(new ShelbyWalk());
                resultText.setText("Pos by walk: " + billy.executeMove(basePose, modify));
                break;
            case "ride":
                billy.setMove(new Ride());
                resultText.setText("Pos by ride: " + billy.executeMove(basePose, modify));
                break;
            case "fly":
                billy.setMove(new FlyMount());
                resultText.setText("Pos by fly: " + billy.executeMove(basePose, modify));
                break;
            default:
                resultText.setText("Incorrect command! Rewrite!");
                break;
        }
    }
    @FXML
    protected void startClickAction() {
        double basePose, modify;
        try {
            String str  = startPos.getText();
            basePose = Double.parseDouble(str);
            str = startModify.getText();
            modify = Double.parseDouble(str);
            executionStrategy(basePose, modify);
        }
        catch (NumberFormatException err) {
            resultText.setText("Bad input! Rewrite!");}
    }
}