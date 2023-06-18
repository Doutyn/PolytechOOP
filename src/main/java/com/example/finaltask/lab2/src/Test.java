package com.example.finaltask.lab2.src;

public class Test {
    private int value = 1;
    private String skip = "1";
    public int getValue() {
        return value;
    }
    @Example(1)
    public void publicMethod(int value, String skip) {
        int i = Integer.parseInt(skip);
        this.value = i * value;
    }
    @Example(2)
    protected void protMethod(String skip) {
        int i = Integer.parseInt(skip);
        this.value += i;
    }
    @Example(3)
    private void privateMethod(int value) {
        this.value *= value;
    }
    public void second(int value, String skip) {
        this.skip += skip;
        this.value -= value;
    }
    protected void prototype(int value) {
        this.value -= value;
    }
    private void least(String skip) {
        int i = Integer.parseInt(skip);
        this.skip += skip;
    }

}
