package com.example.finaltask.lab6.src;

import java.util.Random;

public class AbstractProgram extends Thread {

    private ProgramState state;
    private Thread stateDaemon;
    private boolean startFlag = true;
    public final Object monitor = new Object();

    void rebootAP() {
        this.state = ProgramState.RUNNING;
    }

    public void stopAP() {
        stateDaemon = null;
    }

    public ProgramState getStateAP() {
        return state;
    }

    @Override
    public void run() {
        Thread thread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                try {
                    synchronized (monitor) {
                        if (startFlag) {
                            state = ProgramState.UNKNOWN;
                            startFlag = false;
                        } else {
                            int newState;
                            if (random.nextInt(10) <= 4) {
                                newState = 2;
                            } else if (random.nextInt(10) <= 7) {
                                newState = 1;
                            } else {
                                newState = 3;
                            }
                            state = ProgramState.values()[newState];
                        }
                        monitor.notify();
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
