package com.example.licenta;

public class Users {

    // variables for our coursename,
    // description, tracks and duration, id.


    private String username;
    private String password;


    private Integer status;
    private int id;



    public Users() {
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
    public Users(String username, String password, int id,int status) {
        this.username = username;
        this.password = password;
        this.status=status;
        this.id = id;
    }
}