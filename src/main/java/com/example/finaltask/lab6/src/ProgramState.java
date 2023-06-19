package com.example.finaltask.lab6.src;

import java.util.Random;

enum ProgramState {
    UNKNOWN, STOPPING, RUNNING, FATAL_ERROR;

    public static ProgramState getRandomState() {
        return values()[new Random().nextInt(values().length)];
    }
}