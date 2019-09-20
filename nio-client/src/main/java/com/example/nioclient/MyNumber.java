package com.example.nioclient;

public class MyNumber {
    private int i;

    public MyNumber(int i) {
        this.i = i;
        sleepQuietly();
    }

    public MyNumber() {
        sleepQuietly();
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "MyNumber{" +
                "i=" + i +
                '}';
    }

    private void sleepQuietly() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
