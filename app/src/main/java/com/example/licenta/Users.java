package com.example.licenta;

public class Users {

    // variables for our coursename,
    // description, tracks and duration, id.


    private String username;
    private String password;
    private int id;



    public Users() {
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // constructor
    public Users(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }
}