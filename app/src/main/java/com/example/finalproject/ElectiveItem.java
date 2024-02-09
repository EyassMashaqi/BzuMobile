package com.example.finalproject;
public class ElectiveItem {
    private String id;
    private String name;
    private String hours;

    public ElectiveItem(String id, String name, String hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }
}

