package com.example.licenta;

public class ListModel {

    private String cardTitle;
    private String cardTime;
    private String cardHour;
    private Event event;

    // Constructor
    public ListModel(String course_name, String course_rating, String course_image,Event event) {
        this.cardTitle = course_name;
        this.cardTime = course_rating;
        this.cardHour = course_image;
        this.event = event;
    }

    // Getter and Setter
    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public String getCardHour() {
        return cardHour;
    }

    public void setCardHour(String cardHour) {
        this.cardHour = cardHour;
    }

    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }
}