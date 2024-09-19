package org.example.authservice.models;


public class RefreshToken {
    private int id;
    private int personId;
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RefreshToken() {}
    public RefreshToken(int personId, String token) {
        this.personId = personId;
        this.token = token;

    }
}
