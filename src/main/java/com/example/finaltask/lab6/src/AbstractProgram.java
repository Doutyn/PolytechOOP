package com.example.finaltask.lab6.src;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AbstractProgram implements Runnable {
    @FXML
    private TextArea result;
    private final int timeInterval;
    private ProgramState programState;

    AbstractProgram(TextArea result) {
        programState = ProgramState.UNKNOWN;
        this.result = result;
        result.appendText(Thread.currentThread().threadId() + " Lab starts with state: " + programState + "\n");
        this.timeInterval = 5;
    }

    synchronized ProgramState getState() {
        return programState;
    }

    synchronized void setState(ProgramState programState) {
        result.appendText("State change: " + programState + "\n");
        this.programState = programState;
        notify();
    }

    @Override
    public void run() {
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(timeInterval);
                    setState(ProgramState.getRandomState());
                    result.appendText(Thread.currentThread().threadId() + " Abstract program state changed to " + programState + "\n");
                    if (programState == ProgramState.FATAL_ERROR) {
                        result.appendText(Thread.currentThread().threadId() + " Abstract program fatal error" + "\n");
                        break;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();
        result.appendText(Thread.currentThread().threadId() + " Daemon start" + "\n");
        try {
            daemonThread.join();
            result.appendText(Thread.currentThread().threadId() + " Daemon end" + "\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
