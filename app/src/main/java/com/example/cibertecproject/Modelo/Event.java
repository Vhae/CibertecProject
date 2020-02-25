package com.example.cibertecproject.Modelo;

public class Event {
    String name;
    String schedule;

    public Event(String name, String schedule) {
        this.name = name;
        this.schedule = schedule;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
