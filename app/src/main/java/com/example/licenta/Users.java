package com.example.licenta;

public class Users {

    // variables for our coursename,
    // description, tracks and duration, id.


    private String username;
    private String password;
    private String firstName;



    private String lastName;
    private String email;

    private Integer status;
    private int id;



    public Users() {
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public Users(String username, String password, int id,int status,String firstName,String lastName,String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName=lastName;
        this.email=email;
        this.status=status;
        this.id = id;
    }
}