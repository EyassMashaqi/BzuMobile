package com.example.finalproject;

import java.util.Date;

public class event {
    private String event_name;
    private Date event_date;

    public event(String event_name, Date event_date) {
        this.event_name = event_name;
        this.event_date = event_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }

    @Override
    public String toString() {
        return "event{" +
                "event_name='" + event_name + '\'' +
                ", event_date=" + event_date +
                '}';
    }
}
