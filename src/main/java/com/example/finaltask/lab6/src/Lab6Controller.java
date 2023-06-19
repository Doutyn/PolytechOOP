package com.example.finaltask.lab6.src;

import com.example.finaltask.ControllerApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class Lab6Controller extends ControllerApplication {
    @FXML
    private TextArea result;

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
        AbstractProgram abstractProgram = new AbstractProgram(result);
        Supervisor supervisor = new Supervisor(abstractProgram, result);

        Thread abstractLabThread = new Thread(abstractProgram);
        supervisor.start();

        abstractLabThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        supervisor.stopAbstractProgram();
        supervisor.interrupt();

        try {
            abstractLabThread.join();
            supervisor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        result.appendText(Thread.currentThread().threadId() + " Thread main ending..."  + "\n"  + "\n");
    }
}