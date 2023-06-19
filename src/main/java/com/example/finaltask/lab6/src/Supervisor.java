package com.example.finaltask.lab6.src;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

class Supervisor extends Thread {
    @FXML
    private TextArea result;
    private final AbstractProgram abstractProgram;

    Supervisor(AbstractProgram abstractProgram, TextArea result) {
        this.abstractProgram = abstractProgram;
        this.result = result;
    }

    synchronized void startAbstractProgram() {
        result.appendText(Thread.currentThread().threadId() + " Supervisor: Starting AP" + "\n");
        abstractProgram.setState(ProgramState.RUNNING);
    }

    synchronized void stopAbstractProgram() {
        if (abstractProgram.getState() == ProgramState.RUNNING) {
            result.appendText(Thread.currentThread().threadId() + " Supervisor: Stopping AP" + "\n");
            abstractProgram.setState(ProgramState.STOPPING);
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (abstractProgram) {
                try {
                    abstractProgram.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                ProgramState state = abstractProgram.getState();

                if (state == ProgramState.STOPPING) {
                    result.appendText(Thread.currentThread().threadId() + " Supervisor: Restarting AP\n");
                    startAbstractProgram();
                } else if (state == ProgramState.FATAL_ERROR) {
                    result.appendText(Thread.currentThread().threadId() + " Supervisor: Terminating AP\n");
                    break;
                }
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
