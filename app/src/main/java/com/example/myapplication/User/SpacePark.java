package com.example.myapplication.User;

public class SpacePark {


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SpacePark(String id) {
        this.id = id;
    }

    private String id;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public SpacePark(Boolean status) {
        this.status = status;
    }

    private Boolean status;
}
