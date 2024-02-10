package com.example.finalproject;
public class course {
    private String id;
    private String name;
    private String link;

    public course(String id, String name, String link) {
        this.id = id;
        this.name = name;
        this.link = link;
    }
    public course() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}