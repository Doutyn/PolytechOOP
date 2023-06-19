package com.example.finaltask.lab3.src;

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
import java.util.*;

public class Lab3Controller extends ControllerApplication {

    @FXML
    private TextArea result;

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

    @FXML
    protected void startClickAction() {
        Collection<Chordal> mammals = Arrays.asList(new CommonHedgehog(),
                new Feline(),
                new Manul());
        Collection<Chordal> predatory = Arrays.asList(new Feline(),
                new Manul());
        Collection<Chordal> hedgehogs = List.of(new CommonHedgehog());

        Collection<Hedgehogs> hengehogs1 = new ArrayList<>();
        Collection<Feline> feline1 = new ArrayList<>();
        Collection<Predatory> predatory1 = new ArrayList<>();

        Collection<Chordal> chordal2 = new ArrayList<>();
        Collection<Manul> manul2 = new ArrayList<>();
        Collection<Feline> feline2 = new ArrayList<>();

        Collection<Insectivores> insectivores3 = new ArrayList<>();
        Collection<Predatory> predatory3_1 = new ArrayList<>();
        Collection<Predatory> predatory3_2 = new ArrayList<>();

        segregate(mammals, hengehogs1, feline1, predatory1);
        segregate(predatory, chordal2, manul2, feline2);
        segregate(hedgehogs, insectivores3, predatory3_1, predatory3_2);

        result.appendText(hengehogs1.toString() + feline1 + predatory1 + "\n");
        result.appendText(chordal2.toString() + manul2 + feline2 + "\n");
        result.appendText(insectivores3.toString() + predatory3_1 + predatory3_2 + "\n");
    }

    static public void segregate (Collection<? extends Chordal> srcCollection,
                                  Collection<? super CommonHedgehog> Collection1,
                                  Collection<? super Manul> Collection2,
                                  Collection<? super Feline> Collection3) {
        for (Chordal animals : srcCollection) {
            if (animals instanceof CommonHedgehog) {
                Collection1.add((CommonHedgehog) animals);
            }
            if (animals instanceof Manul) {
                Collection2.add((Manul) animals);
            }
            if (animals instanceof Feline) {
                Collection3.add((Feline) animals);
            }
        }
    }
}

