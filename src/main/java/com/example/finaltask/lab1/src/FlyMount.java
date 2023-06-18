package com.example.finaltask.lab1.src;

public class FlyMount implements Moving{
    @Override
    public double move(double x, double y) {
        return x+(y*4);
    }
}