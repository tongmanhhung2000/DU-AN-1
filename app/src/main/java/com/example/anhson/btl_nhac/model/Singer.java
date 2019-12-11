package com.example.anhson.btl_nhac.model;

/**
 * Created by Anh Son on 4/11/2017.
 */

public class Singer {
    private String name;
    private int numberOfSing;

    public Singer(String name, int numberOfSing) {
        this.name = name;
        this.numberOfSing = numberOfSing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfSing() {
        return numberOfSing;
    }

    public void setNumberOfSing(int numberOfSing) {
        this.numberOfSing = numberOfSing;
    }

    public void addNumberOfSing() {
        numberOfSing++;
    }
}
