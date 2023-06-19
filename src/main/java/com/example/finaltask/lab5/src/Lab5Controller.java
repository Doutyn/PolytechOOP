package com.example.finaltask.lab5.src;

import com.example.finaltask.ControllerApplication;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Lab5Controller extends ControllerApplication {

    private final ObservableList<String> listOfOptions = FXCollections.observableArrayList
            ("1. The average value of a list of integers",
                    "2. Prefix plus uppercase",
                    "3. The square of numbers occurring once",
                    "4. Alphabetically sorted strings with a specific letter",
                    "5. Last element of collection",
                    "6. Sum of even numbers",
                    "7. Strings to map");

    public void handleChoiceBoxAction() {
        int selected = choiceMethod.getSelectionModel().getSelectedIndex();
        if (selected != 3) {
            extr.setVisible(false);
            extraText.setVisible(false);
        } else {
            extr.setVisible(true);
            extraText.setVisible(true);
        }
    }
    @FXML
    private TextField inputText;

    @FXML
    private TextField extraText;

    @FXML
    private TextArea resultText;

    @FXML
    private Label extr;

    @FXML
    private ChoiceBox<String> choiceMethod;

    @FXML
    private void initialize() {
        extr.setVisible(false);
        extraText.setVisible(false);
        choiceMethod.setItems(listOfOptions);
    }

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
        try {
            String method = choiceMethod.getValue();
            switch (method) {
                case "1. The average value of a list of integers" -> {
                    String[] strNum = inputText.getText().split("[,\\s]+");
                    ArrayList<Integer> listNum = new ArrayList<>();
                    for (String s : strNum) {
                        listNum.add(Integer.parseInt(s));
                    }
                    resultText.appendText(Utils.average(listNum) + "\n");
                }
                case "2. Prefix plus uppercase" -> {
                    String[] strWords = inputText.getText().split("[,\\s]+");
                    ArrayList<String> listWords = new ArrayList<>(List.of(strWords));
                    resultText.appendText(Utils.toUpperCaseAndAddPreffix(listWords) + "\n");
                }
                case "3. The square of numbers occurring once" -> {
                    String[] strNmb = inputText.getText().split("[,\\s]+");
                    ArrayList<Integer> listNmb = new ArrayList<>();
                    for (String s : strNmb) {
                        listNmb.add(Integer.parseInt(s));
                    }
                    resultText.appendText(Utils.toSquaredUnique(listNmb) + "\n");
                }
                case "4. Alphabetically sorted strings with a specific letter" -> {
                    String[] wordsToSpl = inputText.getText().split("[,\\s]+");
                    ArrayList<String> listToSort = new ArrayList<>(List.of(wordsToSpl));
                    String extra = extraText.getText();
                    char letter = extra.charAt(0);
                    resultText.appendText(Utils.filterAndSortByStartingLetter(listToSort, letter) + "\n");
                }
                case "5. Last element of collection" -> {
                    String[] findLast = inputText.getText().split("[,\\s]+");
                    ArrayList<String> lastElem = new ArrayList<>(List.of(findLast));
                    resultText.appendText(Utils.getLastElementOrThrow(lastElem) + "\n");
                }
                case "6. Sum of even numbers" -> {
                    String[] strSumEven = inputText.getText().split("[,\\s]+");
                    int[] arrayEven = new int[strSumEven.length];
                    for (int i = 0; i < arrayEven.length; i++) {
                        arrayEven[i] = Integer.parseInt(strSumEven[i]);
                    }
                    resultText.appendText(Utils.getEvenOr0(arrayEven) + "\n");
                }
                case "7. Strings to map" -> {
                    String[] wordsToMap = inputText.getText().split("[,\\s]+");
                    ArrayList<String> listToMap = new ArrayList<>(List.of(wordsToMap));
                    resultText.appendText(Utils.toStringMap(listToMap) + "\n");
                }
                default -> {
                }
            }
        }
        catch (Exception e) {
            resultText.appendText(e.getMessage() + "\n");
        }
    }
}