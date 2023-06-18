package com.example.finaltask.lab1.src;

public class Hero {
    private Moving moving;
    public Hero(){
        setMove(new ShelbyWalk());
    }
    public void setMove(Moving moving)
    {
        this.moving = moving;
    }
    public double executeMove(double x, double y)
    {
        return moving.move(x, y);
    }
}