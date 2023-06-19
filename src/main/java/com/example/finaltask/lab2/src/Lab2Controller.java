package com.example.finaltask.lab2.src;

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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Lab2Controller extends ControllerApplication {
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
        Test example = new Test();
        Class<?> clazz = example.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Example.class)) {
                for (int i = 0; i < method.getAnnotation(Example.class).value(); i++) {
                    method.setAccessible(true);
                    if (!Modifier.isProtected(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers())) {
                        break;
                    }
                    try {
                        var params = Arrays.asList(method.getParameterTypes());
                        if (method.getParameterCount() == 0) {
                            method.invoke(example);
                        } else if (method.getParameterCount() == 1 && params.contains(String.class)) {
                            method.invoke(example, "3");
                        } else if (method.getParameterCount() == 1 && params.contains(int.class)) {
                            method.invoke(example, 3);
                        } else if (method.getParameterCount() == 2) {
                            method.invoke(example, example.getValue(), "7");
                        } else {
                            result.appendText("Error!" + "\n");
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        result.appendText(e + "\n");
                    }
                    result.appendText(method.getName() + ": ");
                    result.appendText(example.getValue() + "\n");
                }
            }
        }
    }
}
