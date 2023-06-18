package com.example.finaltask.lab6.src;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Supervisor extends Thread {

    @FXML
    TextArea result;
    private final AbstractProgram program;

    public Supervisor(AbstractProgram program, TextArea result) {
        this.program = program;
        this.result = result;
    }

    public void startProgram() {
        program.start();
    }

    public void rebootProgram() {
        program.rebootAP();
    }

    public void stopProgram() {
        program.stopAP();
    }

    @Override
    public void run() {
        result.appendText("Active thread: " + Thread.currentThread().getName() + "\n");
        startProgram();
        while (true) {
            synchronized (program.monitor) {
                try {
                    program.monitor.wait();
                    result.appendText("State: " + program.getStateAP() + " " + Thread.currentThread().getName() + "\n");
                    if (program.getStateAP() == ProgramState.STOPPING) {
                        rebootProgram();
                        result.appendText("State: " + program.getStateAP() + " " + Thread.currentThread().getName() + "\n");
                    } else if (program.getStateAP() == ProgramState.FATAL_ERROR) {
                        stopProgram();
                        break;
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
