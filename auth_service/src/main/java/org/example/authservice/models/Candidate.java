package org.example.authservice.models;


public class Candidate{
    private int id;
    private String username;
    private String password;

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

    public String toString() {
        return "Candidate [username=" + username + ", password=" + password + "]";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
