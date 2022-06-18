package com.example.licenta;

import java.util.ArrayList;

public class Event {

    public Event(String title, String owner, String date, String hour, String place, String description, String token, ArrayList<String> participants, int status) {
        this.title = title;
        this.owner = owner;
        this.date = date;
        this.hour = hour;
        this.place = place;
        this.description = description;
        this.token = token;
        this.participants = participants;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String title,owner,date,hour,place,description,token;
    private ArrayList<String> participants;
    private int status;




}