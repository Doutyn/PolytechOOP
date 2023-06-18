package com.example.finaltask.lab1.src;

public class Ride implements Moving{
    @Override
    public double move(double x, double y) {
        return x+(y*2.0);
    }
}